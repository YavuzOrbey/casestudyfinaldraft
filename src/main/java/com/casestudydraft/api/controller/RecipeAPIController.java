package com.casestudydraft.api.controller;

import com.casestudydraft.model.*;
import com.casestudydraft.service.IngredientService;
import com.casestudydraft.service.MeasurementService;
import com.casestudydraft.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
@RestController
@RequestMapping("api")
@CrossOrigin
public class RecipeAPIController {
    @Autowired
    IngredientService ingredientService;

    @Autowired
    MeasurementService measurementService;

    @Autowired
    RecipeService recipeService;
    @RequestMapping(value="/recipe")
    public List<Recipe> findMatchingRecipes(@RequestParam String q) {
        return recipeService.findByNameIgnoreCaseContaining(q);
    }
    @RequestMapping(value="/recipe", method= RequestMethod.POST)
    public  @ResponseBody
    Map<String, String> storeRecipe(@RequestBody String string) throws JsonProcessingException { // Recipe recipe
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonObject = mapper.readTree(string);
        Recipe recipe  = mapper.readValue(string, Recipe.class); // this will try to parse what it can into a Recipe POJO
        JsonNode recipeIngredientsJson = jsonObject.path("recipeIngredients");
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();


        recipeIngredientsJson.forEach(recipeIngredientObj->{
            Ingredient ingredient = ingredientService.get(Long.parseLong(recipeIngredientObj.path("id").toString()));
            int quantity = Integer.parseInt(recipeIngredientObj.path("amount").toString());
            Measurement measurement = measurementService.get(Long.parseLong(recipeIngredientObj.path("measurement").toString()));
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setQuantity(quantity);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredient.setRecipe(recipe);
            recipeIngredients.add(recipeIngredient);
        });

        JsonNode recipeStepsJson = jsonObject.path("recipeSteps");
        List<Step> recipeSteps = new ArrayList<>();
        recipeStepsJson.forEach(recipeStepObj->{
            Step step = new Step(recipeStepObj.path("text").toString(), Integer.parseInt(recipeStepObj.path("stepOrder").toString()));
            step.setRecipe(recipe);
            recipeSteps.add(step);
        });

        recipe.setRecipeIngredients(recipeIngredients);
        recipe.setRecipeSteps(recipeSteps);
        Map<String, String> errors = new HashMap<>();
        //those validation errors actually won't get caught until this point
        try{
            recipeService.save(recipe);
        }
        catch(ConstraintViolationException e){
            //here is where I add each constraint violation error into a hashmap
            // the hashmap will get sent by this controller back as an AJAX response
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            violations.forEach(violation->{
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            });
        }
        return errors;
    }
}
