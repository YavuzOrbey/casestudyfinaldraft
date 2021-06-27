package com.casestudydraft.service;

import com.casestudydraft.model.*;
import com.casestudydraft.repository.PantryRepository;
import com.casestudydraft.tools.IngredientException;
import com.casestudydraft.tools.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PantryService {

    @Autowired
    PantryRepository pantryRepository;
    public void addIngredient(PantryIngredient pantryIngredient){
        //check to see if pantry has that ingredient
        Pantry pantry = pantryIngredient.getPantry();
        List<PantryIngredient> pantryIngredientList = pantry.getPantryIngredients();
        if(pantryIngredientList.contains(pantryIngredient)){
            int index = pantryIngredientList.indexOf(pantryIngredient);
            PantryIngredient ingredientAlreadyIn =  pantryIngredientList.get(index);
            ingredientAlreadyIn.setQuantity(ingredientAlreadyIn.getQuantity()+pantryIngredient.getQuantity());
        }
        else
            pantryIngredientList.add(pantryIngredient);
        pantryRepository.save(pantry);
    }

    public void removeIngredient(PantryIngredient pantryIngredient) throws IngredientException {
        Pantry pantry = pantryIngredient.getPantry();
        List<PantryIngredient> pantryIngredientList = pantry.getPantryIngredients();
        if(pantryIngredientList.contains(pantryIngredient)){
            int index = pantryIngredientList.indexOf(pantryIngredient);
            PantryIngredient ingredientAlreadyIn =  pantryIngredientList.get(index);
            if(ingredientAlreadyIn.getQuantity() - pantryIngredient.getQuantity() < 0 )
                throw new IngredientException("Cannot have negative amount of ingredients"); // LOOK I used a custom exception!
            ingredientAlreadyIn.setQuantity(ingredientAlreadyIn.getQuantity() - pantryIngredient.getQuantity());
        }else{
            throw new IngredientException("Cannot remove what is not in your pantry");
        }
        pantryRepository.save(pantry);
    }

    public boolean contains(Pantry pantry, Ingredient ingredient){
        for(PantryIngredient pantryIngredient: pantry.getPantryIngredients()){
            if(pantryIngredient.getIngredient().equals(ingredient)){
                return true;
            }
        }
        return false;
    }

    public boolean hasEnough(Pantry pantry, Ingredient ingredient, int amount){
        for(PantryIngredient pantryIngredient: pantry.getPantryIngredients()){
            if(pantryIngredient.getIngredient().equals(ingredient) && pantryIngredient.getQuantity() >= amount){
                return true;
            }
        }
        return false;
    }

    public Map<Long, KeyValuePair<Integer, Measurement>> getPantry(Pantry pantry){
        Map<Long, KeyValuePair<Integer, Measurement>> pantryMap = new HashMap<>();
       for(PantryIngredient pantryIngredient: pantry.getPantryIngredients()){
          pantryMap.put(pantryIngredient.getIngredient().getId(), new KeyValuePair<>(pantryIngredient.getQuantity(), pantryIngredient.getMeasurement()) );
       }
       return pantryMap;
    }



}
