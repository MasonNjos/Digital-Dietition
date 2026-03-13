package com.digitaldietitian.controller;

import com.digitaldietitian.entity.NutritionLog;
import com.digitaldietitian.service.NutritionLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionLogController {

    private final NutritionLogService service;

    public NutritionLogController(NutritionLogService service) {
        this.service = service;
    }

    // -------------------------------------------------------------------------
    // POST /api/nutrition/upload
    // Upload a Cronometer PDF report. If a log for the same date already
    // exists it will be replaced (upsert).
    //
    // curl -F "file=@CronometerReport.pdf" http://localhost:8080/api/nutrition/upload
    // -------------------------------------------------------------------------
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<NutritionLog> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            NutritionLog saved = service.parsAndSave(file);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // -------------------------------------------------------------------------
    // GET /api/nutrition
    // Returns all stored nutrition logs
    // -------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<NutritionLog>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // -------------------------------------------------------------------------
    // GET /api/nutrition/{date}
    // Returns the nutrition log for a specific date, e.g. /api/nutrition/2026-03-09
    // -------------------------------------------------------------------------
    @GetMapping("/{date}")
    public ResponseEntity<NutritionLog> getByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getByDate(date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
