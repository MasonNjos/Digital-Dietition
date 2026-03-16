package com.digitaldietitian.service;

import com.digitaldietitian.entity.Food;
import com.digitaldietitian.repository.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;   // constructor injection, no @Autowired needed
    }

    // look up by nutrient
    public List<Food> getFoodsByNutrient(String nutrient) {
        return foodRepository.findByNutrientName(nutrient);
    }

}
