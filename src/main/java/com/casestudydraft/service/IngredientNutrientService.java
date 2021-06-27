package com.casestudydraft.service;


import com.casestudydraft.model.IngredientNutrient;
import com.casestudydraft.repository.IngredientNutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientNutrientService{
    @Autowired
    IngredientNutrientRepository ingredientNutrientRepository;
    public IngredientNutrient findById(Integer id){
        return ingredientNutrientRepository.getById( id);
    }

    public List<IngredientNutrient> findAll() {
        return ingredientNutrientRepository.findAll();
    }
}

