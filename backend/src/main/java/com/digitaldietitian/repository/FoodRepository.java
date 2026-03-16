package com.digitaldietitian.repository;

import com.digitaldietitian.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    // Free CRUD from JpaRepository — add custom queries here later

    @Query("SELECT f FROM Food f JOIN f.nutrients n WHERE LOWER(n.name) = LOWER(:nutrientName)")
    List<Food> findByNutrientName(@Param("nutrientName") String nutrientName);  // find foods that contain a specific nutrient
}