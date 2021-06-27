package com.casestudydraft.repository;

import com.casestudydraft.model.PantryIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PantryIngredientRepository extends JpaRepository<PantryIngredient, Long> {
}
