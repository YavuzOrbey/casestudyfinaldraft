package com.casestudydraft.service;

import com.casestudydraft.model.*;
import com.casestudydraft.repository.RecipeRepository;
import com.casestudydraft.tools.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public void save(Recipe recipe){
        recipe.setUpdated_on(new Timestamp(System.currentTimeMillis()));
        recipeRepository.save(recipe);
    }
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe get(int id){
        return recipeRepository.getById(id);
    }
    public Recipe findByName(String name){
        return recipeRepository.findByName(name);
    }
    public void delete(Recipe recipe){
        recipeRepository.delete(recipe);
    }
    public List<Recipe> findByNameIgnoreCaseContaining(String name) { return recipeRepository.findByNameIgnoreCaseContaining(name);}
    public List<Recipe> findByPublishedAndUser(boolean published, long userId){
        return recipeRepository.findByPublishedAndUsersId(published, userId);
    }

    public Map<String, KeyValuePair<AtomicInteger, String>> calculateNutrition(Recipe recipe){
        Map<String, KeyValuePair<AtomicInteger, String>> nutrition = new HashMap<>();
        AtomicInteger calories = new AtomicInteger();
        AtomicInteger servingSize = new AtomicInteger();
        recipe.getRecipeIngredients().forEach(recipeIngredient -> {
            int amountOfIngredientUsed = recipeIngredient.getQuantity();

           // Measurement measurementOfIngredientUsed = recipeIngredient.getMeasurement();
            recipeIngredient.getIngredient().getCalories();
            float ratio =  amountOfIngredientUsed/(float) recipeIngredient.getIngredient().getServingSize();

            servingSize.addAndGet(amountOfIngredientUsed);
            calories.addAndGet((int) ( recipeIngredient.getIngredient().getCalories() * ratio));

            AtomicInteger sum = new AtomicInteger();
            recipeIngredient.getIngredient().getIngredientNutrients().forEach(ingredientNutrient -> {
                sum.addAndGet((int) (ingredientNutrient.getAmount()* ratio));
                nutrition.put(ingredientNutrient.getNutrient().getName(),
                        new KeyValuePair<AtomicInteger, String>(sum,ingredientNutrient.getNutrient().getMeasurement().getName()));
            });
        });
        nutrition.put("calories", new KeyValuePair<>(calories, ""));
        nutrition.put("servingSize", new KeyValuePair<>(servingSize, ""));
        System.out.println("At end of calculateNutrition Method" + nutrition);
        return nutrition;
    }
}
