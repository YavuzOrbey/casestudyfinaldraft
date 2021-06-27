package com.casestudydraft.repository;

import com.casestudydraft.model.Ingredient;
import com.casestudydraft.model.Recipe;
import com.casestudydraft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Recipe findByName(String name);
    List<Recipe> findByNameIgnoreCaseContaining(String name);
    List<Recipe> findByPublishedAndUsersId(boolean published, Long userId);
}
