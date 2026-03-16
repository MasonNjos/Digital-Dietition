package com.digitaldietitian;

import com.digitaldietitian.entity.Food;
import com.digitaldietitian.repository.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

   @Bean
public CommandLineRunner testData(FoodRepository foodRepository) {
    return args -> {
        List<Food> foods = foodRepository.findAll();
        System.out.println("=== FOOD TABLE (" + foods.size() + " rows) ===");
        for (Food f : foods) {
            System.out.println(f.getName() + " | " + f.getServingSize());
        }
    };
}
}