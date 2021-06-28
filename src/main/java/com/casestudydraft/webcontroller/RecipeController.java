package com.casestudydraft.webcontroller;

import com.casestudydraft.model.*;
import com.casestudydraft.service.*;
import com.casestudydraft.tools.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    NutrientService nutrientService;
    @Autowired
    MeasurementService measurementService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    IngredientNutrientService ingredientNutrientService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;
    @ModelAttribute("ingredient")
    public Ingredient setUpIngredient(){
        Ingredient ingredient = new Ingredient();
        return ingredient;
    }

    @ModelAttribute("measurements")
    public ArrayList<Measurement> measurements(){

        List<Measurement> measurements = measurementService.listAll();
        return (ArrayList<Measurement>) measurements;
    }

    @ModelAttribute("nutrients")
    public ArrayList<Nutrient> nutrients(){

        List<Nutrient> nutrients = nutrientService.findAll();
        return (ArrayList<Nutrient>) nutrients;
    }

    @ModelAttribute("ingredients")
    public ArrayList<Ingredient> ingredients(){
        List<Ingredient> ingredients = ingredientService.findAll();
        return (ArrayList<Ingredient>) ingredients;
    }
    @ModelAttribute("recipes")
    public ArrayList<Recipe> recipes(){
        List<Recipe> recipes = recipeService.findAll();
        return (ArrayList<Recipe>) recipes;
    }
/*    @ModelAttribute("myrecipes")
    public ArrayList<Recipe> myRecipes(){
        List<Recipe> recipes = recipeService.findByPublishedAndUser(false, userService.findByUsername("yavuz1").getId());
        return (ArrayList<Recipe>) recipes;
    }*/

    @RequestMapping(value="", method= RequestMethod.GET)
    public String redirectToMain(){
        return "redirect:recipe/";
    }
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView viewAllRecipes(HttpServletRequest request, @ModelAttribute("recipes") ArrayList<Recipe> recipes) {
        ModelAndView mav = null;
        mav = new ModelAndView("recipe/index");
        return mav;
    }

    @RequestMapping("/myrecipes")
    public ModelAndView viewMyRecipes(HttpServletRequest request, @ModelAttribute("recipes") ArrayList<Recipe> recipes) {
        ModelAndView mav = null;
        mav = new ModelAndView("recipe/index");
        return mav;
    }
    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView createRecipe(@ModelAttribute("measurements") ArrayList<Measurement> measurements,
                                     @ModelAttribute("ingredients") ArrayList<Ingredient> ingredients,
                                     @ModelAttribute("recipes") ArrayList<Recipe> recipes
                                     ){
        ModelAndView mav = null;
        mav = new ModelAndView("recipe/create");
        mav.addObject("recipe", new Recipe());
        return mav;
    }

    //not used anymore..creating Recipe via POST API Call
    /*@RequestMapping(value="/create", method= RequestMethod.POST)
    public ModelAndView storeRecipe(HttpServletRequest request,
                                        @ModelAttribute("recipe") Recipe recipe,
                                        BindingResult result) {
        ModelAndView mav = null;
        if(result.hasErrors()){
            mav = new ModelAndView("recipe/create");
            return mav;

        }
        //maybe stop trying to bind recipe.recipeIngredients as well as all other stuff that ALREADY exists
        System.out.println(recipe.getRecipeIngredients());
        recipe.getRecipeIngredients().forEach(recipeIngredient -> {
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredientService.get(recipeIngredient.getIngredient().getId()));
            recipeIngredient.setMeasurement(measurementService.get(recipeIngredient.getMeasurement().getId()));
        });
        recipeService.save(recipe);
        mav = new ModelAndView("recipe/index");
        return mav;
    }*/
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ModelAndView viewRecipe(HttpServletRequest request, @PathVariable Integer id) {
        ModelAndView mav = null;
        Recipe recipe = recipeService.get(id);
        mav = new ModelAndView("recipe/view");
        /*Map<String, AtomicInteger> recipeInfo = new HashMap<>();
        //I didn't realize that data structure I made a while ago would finally come in handy!

        //EDIT: 6/22/21 Midnight maybe move all of this into the recipeservice class and call it calculateNutrition
        Map<String, KeyValuePair<AtomicInteger, String>> recipeInfo2 = new HashMap<>();
        // calculate recipe nutrients based on individual ingredient nutrients
        //have to be really careful here this can get confusing real fast

        AtomicInteger calories = new AtomicInteger();
        AtomicInteger servingSize = new AtomicInteger();
         recipe.getRecipeIngredients().forEach(recipeIngredient -> {   //get the recipe_ingredients used in the recipe
             int amountOfIngredientUsed = recipeIngredient.getQuantity();
             System.out.println(amountOfIngredientUsed);
             Measurement measurementOfIngredientUsed = recipeIngredient.getMeasurement(); // Remember we're CHOOSING the amount of ingredient used
             recipeIngredient.getIngredient().getCalories(); // this is the amount of calories in the ingredient given the normal serving and measurement (i.e.
             // 440 calories in 120 g of bread flour

             //if we use 1000 g of bread flour, which is 1000/120 = 8.33333 times as much as a normal serving, we should expect
             // 440*8.333333 =3666.66652 calories from the bread flour in the recipe


             //first convert amountOfIngredientUsed to the ingredient's measurement

             //FOR SIMPLICITY I NEED TO JUST KEEP EVERYTHING IN GRAMS FOR NOW

             //LATER ON I'll ADD WEIGHT TO VOLUME and vice versa conversions
             float ratio =  amountOfIngredientUsed/(float) recipeIngredient.getIngredient().getServingSize();
             System.out.println(ratio);
             servingSize.addAndGet(amountOfIngredientUsed);
             calories.addAndGet((int) ( recipeIngredient.getIngredient().getCalories() * ratio));

             AtomicInteger sum = new AtomicInteger();
             recipeIngredient.getIngredient().getIngredientNutrients().forEach(ingredientNutrient -> { //get the current ingredient and get nutrients inside of it
                 *//*System.out.println(ingredientNutrient.getIngredient().getName() + " has " + ingredientNutrient.getAmount() + " " +
                                 ingredientNutrient.getNutrient().getMeasurement().getName() + " of " + ingredientNutrient.getNutrient().getName());*//*
                 sum.addAndGet((int) (ingredientNutrient.getAmount()* ratio));
                 recipeInfo.put(ingredientNutrient.getNutrient().getName(), sum);
                 recipeInfo2.put(ingredientNutrient.getNutrient().getName(),
                         new KeyValuePair<AtomicInteger, String>(sum,ingredientNutrient.getNutrient().getMeasurement().getName()));
             });
         });*/

        //I left the above in as it was very difficult and it shows my pain in creating this app
        Map<String, KeyValuePair<AtomicInteger, String>> nutrition = recipeService.calculateNutrition(recipe);
        mav.addObject("nutrition", nutrition);
        mav.addObject("recipe", recipe);
        URL resource = getClass().getClassLoader().getResource("static/images/recipe-" + recipe.getId() + ".jpg");
        Boolean image;
        if (resource == null) {
            image = false;
        }else{
            image = true;
        }
        mav.addObject("image", image);
        return mav;
    }
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteRecipe(@PathVariable int id){
        recipeService.delete(recipeService.get(id));
        return "redirect:/recipe/";
    }

}
