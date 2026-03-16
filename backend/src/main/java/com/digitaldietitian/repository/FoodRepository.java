package com.digitaldietitian.repository;

import com.digitaldietitian.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    // Free CRUD from JpaRepository — add custom queries here later
}