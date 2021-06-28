package com.casestudydraft.api.controller;

import com.casestudydraft.model.*;
import com.casestudydraft.service.*;
import com.casestudydraft.tools.IngredientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("api")
public class PantryAPIController {

    //Autowirings
    @Autowired
    PantryService pantryService;
    @Autowired
    UserService userService;
    @Autowired
    MeasurementService measurementService;
    @Autowired
    RecipeService recipeService;
    @Autowired
    IngredientService ingredientService;

    @RequestMapping(value="/addIngredients", method=RequestMethod.POST)
    public String addIngredientsToPantry(@RequestBody String string, Principal principal) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode jsonObject = mapper.readTree(string);
        //find currently logged in user via the Principal and get their pantry
        User user = userService.findByUsername(principal.getName());
        Pantry pantry = user.getPantry();

        Ingredient ingredient = ingredientService.get(jsonObject.path("id").asLong());

        /* When saving an ingredient into a user's pantry we save it as as PantryIngredient, another entity for the manytomany relationship
        between Ingredients and Pantries. A pantry can have many ingredients and an ingredient can be in many pantries. However I also needed
        to know how MUCH of that ingredient was in a pantry so another entity was required.

        I use the setters of PantryIngredient to make sure it has all of it's associated fields set
        */
        PantryIngredient pantryIngredient = new PantryIngredient();
        pantryIngredient.setIngredient(ingredient);
        pantryIngredient.setMeasurement(measurementService.get(jsonObject.path("measurement").asLong()));
        pantryIngredient.setPantry(pantry);
        pantryIngredient.setQuantity(jsonObject.path("quantity").asInt());

        pantryService.addIngredient(pantryIngredient);
        return "DONE";
    }
    @RequestMapping(value="/checkpantry")
    public void makeRecipe(@RequestParam Integer recipeId, Principal principal) throws IngredientException {
        User user = userService.findByUsername(principal.getName());
        //how many ingredients does recipe require?
        Recipe recipe = recipeService.get(recipeId);
        boolean readyToMake = true;
        for (RecipeIngredient recipeIngredient: recipe.getRecipeIngredients()) {
            // loop through the ingredient's of the recipe and if the user's pantry doesn't have the quantity of that specific ingredient
            // make sure to through an ingredientexception
            if(!pantryService.hasEnough(user.getPantry(),recipeIngredient.getIngredient(),recipeIngredient.getQuantity())){
                readyToMake = false;
                throw new IngredientException("Not Enough ingredients!");
            }
        }

        if(readyToMake){
            for (RecipeIngredient recipeIngredient: recipe.getRecipeIngredients()) {
                PantryIngredient pantryIngredient = new PantryIngredient();
                pantryIngredient.setIngredient(recipeIngredient.getIngredient());
                pantryIngredient.setPantry(user.getPantry());
                pantryIngredient.setQuantity(recipeIngredient.getQuantity());
                pantryService.removeIngredient(pantryIngredient);
            }
        }
    }
}
