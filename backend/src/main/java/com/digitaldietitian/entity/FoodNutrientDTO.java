package com.digitaldietitian.entity;

import com.digitaldietitian.entity.Food;
import com.digitaldietitian.entity.NutrientEntry;
import java.util.List;

public class FoodNutrientDTO {

    private NutrientEntry nutrient;       // the nutrient the user is falling short on
    private List<Food> recommendedFoods;  // foods high in that nutrient

    public FoodNutrientDTO(NutrientEntry nutrient, List<Food> recommendedFoods) {
        this.nutrient = nutrient;
        this.recommendedFoods = recommendedFoods;
    }

    public NutrientEntry getNutrient() { return nutrient; }
    public List<Food> getRecommendedFoods() { return recommendedFoods; }
}