package com.digitaldietitian.repository;

import com.digitaldietitian.entity.NutritionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface NutritionLogRepository extends JpaRepository<NutritionLog, Long> {

    Optional<NutritionLog> findByLogDate(LocalDate logDate);

    boolean existsByLogDate(LocalDate logDate);
}
