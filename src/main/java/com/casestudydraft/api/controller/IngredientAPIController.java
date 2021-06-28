package com.casestudydraft.api.controller;

import com.casestudydraft.model.Ingredient;
import com.casestudydraft.model.IngredientNutrient;
import com.casestudydraft.model.Measurement;
import com.casestudydraft.service.IngredientService;
import com.casestudydraft.service.MeasurementService;
import com.casestudydraft.service.NutrientService;
import com.casestudydraft.tools.IngredientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*Because my app is using a lot of javascript and making AJAX requests I needed to make my own API Endpoints to retrieve
my data */

@RestController
@RequestMapping("api")
@CrossOrigin
public class IngredientAPIController {


    //Autowiring the services I'm going to use in this class
    @Autowired
    IngredientService ingredientService;

    @Autowired
    NutrientService nutrientService;

    @Autowired
    MeasurementService measurementService;


    @ModelAttribute("ingredients")
    public ArrayList<Ingredient> ingredients(){
        List<Ingredient> ingredients = ingredientService.findAll();
        return (ArrayList<Ingredient>) ingredients;
    }

    // the route localhost:8080/api/ingredients will send a json string with all of the ingredients in the database
    @RequestMapping(value = "/ingredients")
    public List<Ingredient> viewAllIngredients(@ModelAttribute("ingredients") List<Ingredient> ingredients) {
        return ingredients;
    }

    @RequestMapping(value = "/ingredient")
    public List<Ingredient> findMatchingIngredients(@RequestParam String q) {
        return ingredientService.findByNameIgnoreCaseContaining(q);
    }

    @RequestMapping(value = "/ingredient", method = RequestMethod.POST)
    public @ResponseBody
    String storeIngredient(@RequestBody String string) throws JsonProcessingException, IngredientException {
        System.out.println(string);
        /*  The string that goes through this method is a JSON string in a specific format
         * {
         * "name": ...,
         * "servingSize": ...,
         * "measurement": ...,
         *  etc...
         * }
         */

        //Need to have fail on unknown to be false otherwise it would throw an exception if it can't map to an Ingredient
        // and not every property in the json maps 1:1
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // this will try to parse what it can into a Ingredient pojo minus the stuff I explitcitly told it not to in the model
        Ingredient ingredient = mapper.readValue(string, Ingredient.class);

        // this is for the other stuff that can't be parsed
        JsonNode jsonObject = mapper.readTree(string);

        //if there is already ingredient with the same name throw an IngredientException (custom exception)
        if (ingredientService.findByName(ingredient.getName()) != null)
            throw new IngredientException("Duplicate Value!");

        /*The measurement property in the JSON string is only a number so it's not going to be able to parse it to a Measurement
        so we're just going to get the number, which is the id of a measurement, use it to get the measurement via the service and
        set the measurement on the ingredient which was parsed to that ingredient. I mention it here in depth because it will be used
        several times later for other properties
         */
        JsonNode measurementJson = jsonObject.path("measurement");
        Measurement measurement = measurementService.get(Long.parseLong(measurementJson.toString()));
        ingredient.setMeasurement(measurement);


        JsonNode nutrients = jsonObject.path("nutrients");
        List<IngredientNutrient> ingredientNutrients = new ArrayList<>();
        nutrients.forEach(nutrient -> {
            try {
                JsonNode currentNutrient = mapper.readTree(nutrient.toString());

                IngredientNutrient ingredientNutrient = new IngredientNutrient(nutrientService.get(Long.parseLong(currentNutrient.path("id").toString())));
                ingredientNutrient.setIngredient(ingredient);
                ingredientNutrient.setAmount(Integer.parseInt(currentNutrient.path("amount").toString()));
                ingredientNutrients.add(ingredientNutrient);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        ingredient.setIngredientNutrients(ingredientNutrients);
        ingredientService.save(ingredient);
        return "Completed";
    }


    @RequestMapping(value = "/ingredient/{id}")
    public Ingredient findIngredient(@PathVariable Long id) {
        return ingredientService.get(id);
    }
}
