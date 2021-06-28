package com.casestudydraft.service;

import com.casestudydraft.model.Ingredient;

import com.casestudydraft.model.Nutrient;
import com.casestudydraft.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;
    public Ingredient get(long id){
        return ingredientRepository.getById(id);
    }
    public Ingredient save(Ingredient ingredient){
        ingredient.setUpdated_on(new Timestamp(System.currentTimeMillis()));
        return ingredientRepository.save(ingredient);
    }
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
    public Ingredient findByName(String name) {
        return ingredientRepository.findByName(name);
    }
    public List<Ingredient> findByNameIgnoreCaseContaining(String name) { return ingredientRepository.findByNameIgnoreCaseContaining(name);}
    public void delete(Ingredient ingredient){
        ingredientRepository.delete(ingredient);
    }


}
