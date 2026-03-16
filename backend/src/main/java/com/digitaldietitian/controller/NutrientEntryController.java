package com.digitaldietitian.controller;

import com.digitaldietitian.entity.NutritionLog;
import com.digitaldietitian.service.NutritionLogService;
import com.digitaldietitian.entity.NutrientEntry;
import com.digitaldietitian.service.NutrientEntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.digitaldietitian.entity.Food;
import com.digitaldietitian.service.FoodService;
import com.digitaldietitian.entity.FoodNutrientDTO;

@RestController
@RequestMapping("/api/nutrition")
public class NutrientEntryController {
    private final NutrientEntryService service;

    public NutrientEntryController(NutrientEntryService service) {
        this.service = service;
    }

    

    // -------------------------------------------------------------------------
    // GET /api/nutrition/under
    // returns all nutrients x% under the goal
    // --------------------------------------------------------------------------
    @GetMapping("/under/{threshold}")
    public ResponseEntity<List<FoodNutrientDTO>> getUnder(@PathVariable double threshold) {

        List<FoodNutrientDTO> entries = service.getOutOfRange(threshold);
        return ResponseEntity.ok(entries);
    }

    // -------------------------------------------------------------------------
    // GET /api/nutrition/over
    // returns all nutrients at least 10% over the goal
    // --------------------------------------------------------------------------
    @GetMapping("/over/{threshold}")
    public ResponseEntity<List<FoodNutrientDTO>> getOver(@PathVariable double threshold) {
        threshold += 100.0; // 110% of the goal means 10% over
        List<FoodNutrientDTO> entries = service.getOutOfRange(threshold);
        return ResponseEntity.ok(entries);
    }
    
}
