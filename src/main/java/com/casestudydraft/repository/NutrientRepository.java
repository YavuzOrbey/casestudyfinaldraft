package com.casestudydraft.repository;

import com.casestudydraft.model.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
    Nutrient findByName(String name);
    List<Nutrient> findByNameIgnoreCaseContaining(String name);
}
