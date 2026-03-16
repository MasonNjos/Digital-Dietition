package com.digitaldietitian.service;

import com.digitaldietitian.entity.NutrientEntry;
import com.digitaldietitian.entity.NutritionLog;
import com.digitaldietitian.repository.NutritionLogRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import com.digitaldietitian.entity.FoodNutrientDTO;
import com.digitaldietitian.service.FoodService;
import java.util.stream.Collectors;


@Service
public class NutrientEntryService {
    
    private final NutritionLogRepository repository;
    private final FoodService foodService;

    
    public NutrientEntryService(NutritionLogRepository repository, FoodService foodService) {
        this.repository = repository;
        this.foodService = foodService;
    }

    public List<FoodNutrientDTO> getOutOfRange(double thresholdPct) {
    List<NutritionLog> allLogs = repository.findAll();
    List<FoodNutrientDTO> dtoList = new ArrayList<>();

    for (NutritionLog log : allLogs) {
        List<NutrientEntry> result = new ArrayList<>();  // reset per log

        for (NutrientEntry entry : log.getAllNutrients()) {
            if (thresholdPct > 100.0) { // over case
                if (entry.getPct() != null && entry.getPct() > thresholdPct) {
                    result.add(entry);
                }
            } else { // under case
                if (entry.getPct() != null && entry.getPct() < thresholdPct) {
                    result.add(entry);
                }
            }
        }

        // build DTOs for this log and add to master list
        result.stream()
            .map(entry -> new FoodNutrientDTO(
                entry,
                foodService.getFoodsByNutrient(entry.getName())
            ))
            .forEach(dtoList::add);
    }

    return dtoList;
}


  
}
