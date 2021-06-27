package com.casestudydraft.service;

import com.casestudydraft.model.PantryIngredient;
import com.casestudydraft.repository.PantryIngredientRepository;
import com.casestudydraft.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PantryIngredientService {
    @Autowired
    PantryIngredientRepository pantryIngredientRepository;

    public void save(PantryIngredient pantryIngredient){
        pantryIngredientRepository.save(pantryIngredient);
    }

    public PantryIngredient get(Long id){
        return pantryIngredientRepository.getById(id);
    }
}
