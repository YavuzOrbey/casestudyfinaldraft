package com.casestudydraft.repository;

import com.casestudydraft.model.Ingredient;
import com.casestudydraft.model.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PantryRepository extends JpaRepository<Pantry, Integer> {
}
