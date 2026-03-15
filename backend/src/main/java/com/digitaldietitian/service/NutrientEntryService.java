package com.digitaldietitian.service;

import com.digitaldietitian.entity.NutrientEntry;
import com.digitaldietitian.entity.NutritionLog;
import com.digitaldietitian.repository.NutritionLogRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class NutrientEntryService {
    
    private final NutritionLogRepository repository;
    
    public NutrientEntryService(NutritionLogRepository repository) {
        this.repository = repository;
    }

    // get all nutrients that are out of range by a certain percentage (either under or over)
    public List<NutrientEntry> getOutOfRange(double thresholdPct) {
        List<NutritionLog> allLogs = repository.findAll();
        List<NutrientEntry> result = new ArrayList<>();

        for (NutritionLog log : allLogs) {
            NutritionLog underLog = new NutritionLog();
            underLog.setLogDate(log.getLogDate());

            // get all nutrients and filter by pct < thresholdPct
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

        }

        return result;

    }
  
}
