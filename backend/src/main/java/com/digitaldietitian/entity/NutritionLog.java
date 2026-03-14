package com.digitaldietitian.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nutrition_log")
public class NutritionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_date", unique = true, nullable = false)
    private LocalDate logDate;

    // General: Energy (kcal), Alcohol (g), Caffeine (mg), Water (g)
    @ElementCollection
    @CollectionTable(name = "nutrition_general", joinColumns = @JoinColumn(name = "nutrition_log_id"))
    private List<NutrientEntry> general = new ArrayList<>();

    // Carbohydrates: Carbs, Net Carbs, Fiber, Starch, Sugars — all in g
    @ElementCollection
    @CollectionTable(name = "nutrition_carbohydrates", joinColumns = @JoinColumn(name = "nutrition_log_id"))
    private List<NutrientEntry> carbohydrates = new ArrayList<>();

    // Lipids: Fat, Monounsaturated, Polyunsaturated, Omega-3, Omega-6,
    //         Saturated, Trans-Fats (all g), Cholesterol (mg)
    @ElementCollection
    @CollectionTable(name = "nutrition_lipids", joinColumns = @JoinColumn(name = "nutrition_log_id"))
    private List<NutrientEntry> lipids = new ArrayList<>();

    // Protein: Protein + amino acids (Cystine, Histidine, Isoleucine,
    //          Leucine, Lysine, Methionine, Phenylalanine, Threonine,
    //          Tryptophan, Tyrosine, Valine) — all in g
    @ElementCollection
    @CollectionTable(name = "nutrition_protein", joinColumns = @JoinColumn(name = "nutrition_log_id"))
    private List<NutrientEntry> protein = new ArrayList<>();

    // Vitamins: B1 (mg), B2 (mg), B3 (mg), B5 (mg), B6 (mg),
    //           B12 (µg), Folate (µg), Vitamin C (mg),
    //           Vitamin D (IU), Vitamin E (mg), Vitamin K (µg)
    @ElementCollection
    @CollectionTable(name = "nutrition_vitamins", joinColumns = @JoinColumn(name = "nutrition_log_id"))
    private List<NutrientEntry> vitamins = new ArrayList<>();

    // Minerals: Calcium (mg), Copper (mg), Iron (mg), Magnesium (mg),
    //           Manganese (mg), Phosphorus (mg), Potassium (mg),
    //           Selenium (µg), Sodium (mg), Zinc (mg)
    @ElementCollection
    @CollectionTable(name = "nutrition_minerals", joinColumns = @JoinColumn(name = "nutrition_log_id"))
    private List<NutrientEntry> minerals = new ArrayList<>();

    public NutritionLog() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getLogDate() { return logDate; }
    public void setLogDate(LocalDate logDate) { this.logDate = logDate; }

    public List<NutrientEntry> getGeneral() { return general; }
    public void setGeneral(List<NutrientEntry> general) { this.general = general; }

    public List<NutrientEntry> getCarbohydrates() { return carbohydrates; }
    public void setCarbohydrates(List<NutrientEntry> carbohydrates) { this.carbohydrates = carbohydrates; }

    public List<NutrientEntry> getLipids() { return lipids; }
    public void setLipids(List<NutrientEntry> lipids) { this.lipids = lipids; }

    public List<NutrientEntry> getProtein() { return protein; }
    public void setProtein(List<NutrientEntry> protein) { this.protein = protein; }

    public List<NutrientEntry> getVitamins() { return vitamins; }
    public void setVitamins(List<NutrientEntry> vitamins) { this.vitamins = vitamins; }

    public List<NutrientEntry> getMinerals() { return minerals; }
    public void setMinerals(List<NutrientEntry> minerals) { this.minerals = minerals; }

    public List<NutrientEntry> getAllNutrients() {
        List<NutrientEntry> all = new ArrayList<>();
        all.addAll(general);
        all.addAll(carbohydrates);
        all.addAll(lipids);
        all.addAll(protein);
        all.addAll(vitamins);
        all.addAll(minerals);
        return all;
    }
}
