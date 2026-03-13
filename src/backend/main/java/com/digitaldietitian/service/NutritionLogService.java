package com.digitaldietitian.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digitaldietitian.entity.NutrientEntry;
import com.digitaldietitian.entity.NutritionLog;
import com.digitaldietitian.repository.NutritionLogRepository;

@Service
public class NutritionLogService {

    private final NutritionLogRepository repository;

    // Matches lines like: "Fiber 42.8 g 113%"  or  "Energy 1985.1 kcal 72%"
    // Also handles lines with no pct (N/T or n/a), e.g. "Alcohol 0.0 g N/T"
    private static final Pattern NUTRIENT_LINE = Pattern.compile(
        "^(.+?)\\s+([\\d.]+)\\s+(g|mg|µg|ug|IU|kcal|mg/dL|ms|bpm|%)\\s+([\\d.]+%|N/T|n/a)?\\s*$",
        Pattern.CASE_INSENSITIVE
    );

    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);

    public NutritionLogService(NutritionLogRepository repository) {
        this.repository = repository;
    }

    // -------------------------------------------------------------------------
    // Parse and save a Cronometer PDF report
    // -------------------------------------------------------------------------
    public NutritionLog parsAndSave(MultipartFile file) throws IOException {
        String text = extractText(file);
        NutritionLog log = parseNutrients(text);

        // Upsert: if a log for this date already exists, replace it
        Optional<NutritionLog> existing = repository.findByLogDate(log.getLogDate());
        existing.ifPresent(e -> log.setId(e.getId()));

        return repository.save(log);
    }

    // -------------------------------------------------------------------------
    // Retrieve by date
    // -------------------------------------------------------------------------
    public Optional<NutritionLog> getByDate(LocalDate date) {
        return repository.findByLogDate(date);
    }

    public List<NutritionLog> getAll() {
        return repository.findAll();
    }

    // -------------------------------------------------------------------------
    // PDF text extraction via PDFBox
    // -------------------------------------------------------------------------
        private String extractText(MultipartFile file) throws IOException {
            byte[] bytes = file.getInputStream().readAllBytes();
            try (PDDocument doc = Loader.loadPDF(bytes)) {
                PDFTextStripper stripper = new PDFTextStripper();
                // Page 3 contains the nutrients section in the Cronometer report
                stripper.setStartPage(3);
                stripper.setEndPage(3);
                return stripper.getText(doc);
            }
        }

    // -------------------------------------------------------------------------
    // Parse the nutrients section from extracted text
    // -------------------------------------------------------------------------
    private NutritionLog parseNutrients(String text) {
        NutritionLog log = new NutritionLog();

        List<NutrientEntry> general = new ArrayList<>();
        List<NutrientEntry> carbohydrates = new ArrayList<>();
        List<NutrientEntry> lipids = new ArrayList<>();
        List<NutrientEntry> protein = new ArrayList<>();
        List<NutrientEntry> vitamins = new ArrayList<>();
        List<NutrientEntry> minerals = new ArrayList<>();

        // Extract the date from "Nutrients for Monday, March 9, 2026"
        Pattern datePattern = Pattern.compile("Nutrients for \\w+, (\\w+ \\d+, \\d{4})");
        Matcher dateMatcher = datePattern.matcher(text);
        if (dateMatcher.find()) {
            log.setLogDate(LocalDate.parse(dateMatcher.group(1),
                DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)));
        } else {
            log.setLogDate(LocalDate.now());
        }

        // Determine which section we are currently in
        Section currentSection = Section.NONE;

        for (String raw : text.split("\n")) {
            String line = raw.trim();
            if (line.isEmpty()) continue;

            // Section headers
            if (line.equalsIgnoreCase("General"))        { currentSection = Section.GENERAL;       continue; }
            if (line.equalsIgnoreCase("Carbohydrates"))  { currentSection = Section.CARBOHYDRATES; continue; }
            if (line.equalsIgnoreCase("Lipids"))         { currentSection = Section.LIPIDS;        continue; }
            if (line.equalsIgnoreCase("Protein"))        { currentSection = Section.PROTEIN;       continue; }
            if (line.equalsIgnoreCase("Vitamins"))       { currentSection = Section.VITAMINS;      continue; }
            if (line.equalsIgnoreCase("Minerals"))       { currentSection = Section.MINERALS;      continue; }

            // Skip lines outside a known section
            if (currentSection == Section.NONE) continue;

            // Try to parse a nutrient line
            NutrientEntry entry = parseLine(line);
            if (entry == null) continue;

            switch (currentSection) {
                case GENERAL       -> general.add(entry);
                case CARBOHYDRATES -> carbohydrates.add(entry);
                case LIPIDS        -> lipids.add(entry);
                case PROTEIN       -> protein.add(entry);
                case VITAMINS      -> vitamins.add(entry);
                case MINERALS      -> minerals.add(entry);
                default            -> {}
            }
        }

        log.setGeneral(general);
        log.setCarbohydrates(carbohydrates);
        log.setLipids(lipids);
        log.setProtein(protein);
        log.setVitamins(vitamins);
        log.setMinerals(minerals);

        return log;
    }

    // -------------------------------------------------------------------------
    // Parse a single nutrient line into a NutrientEntry
    // Examples:
    //   "Energy 1985.1 kcal 72%"
    //   "Fiber 42.8 g 113%"
    //   "Alcohol 0.0 g N/T"
    //   " Net Carbs 101.0 g 78%"   (leading spaces already trimmed)
    // -------------------------------------------------------------------------
    private NutrientEntry parseLine(String line) {
        // Remove leading spaces used for sub-items (e.g. " Net Carbs")
        line = line.replaceAll("^\\s+", "");

        Matcher m = NUTRIENT_LINE.matcher(line);
        if (!m.matches()) return null;

        String name  = m.group(1).trim();
        double value = Double.parseDouble(m.group(2));
        String unit  = m.group(3);
        String pctRaw = m.group(4);

        // Normalise µg variants
        if ("ug".equalsIgnoreCase(unit)) unit = "µg";

        Double pct = null;
        if (pctRaw != null && pctRaw.endsWith("%")) {
            pct = Double.parseDouble(pctRaw.replace("%", ""));
        }
        // N/T and n/a are stored as null pct

        return new NutrientEntry(name, value, unit, pct);
    }

    // -------------------------------------------------------------------------
    // Section enum for state-machine parsing
    // -------------------------------------------------------------------------
    private enum Section {
        NONE, GENERAL, CARBOHYDRATES, LIPIDS, PROTEIN, VITAMINS, MINERALS
    }
}
