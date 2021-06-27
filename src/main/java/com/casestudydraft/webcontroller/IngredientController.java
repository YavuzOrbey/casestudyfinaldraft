package com.casestudydraft.webcontroller;

import com.casestudydraft.model.*;
import com.casestudydraft.service.*;
import com.casestudydraft.tools.FormHelper;
import com.casestudydraft.tools.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired NutrientService nutrientService;
    @Autowired MeasurementService measurementService;
    @Autowired IngredientService ingredientService;
    @Autowired IngredientNutrientService ingredientNutrientService;
    @Autowired
    UserService userService;
    @Autowired
    PantryService pantryService;

    @Autowired
    RoleService roleService;
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

    @ModelAttribute("form")
    public FormHelper form(){
        return new FormHelper();
    }
    @RequestMapping(value="", method= RequestMethod.GET)
    public String redirectToMain(){
        return "redirect:ingredient/";
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String viewAllIngredients(HttpServletRequest request, @ModelAttribute("ingredients") ArrayList<Ingredient> ingredients, Authentication authentication, Model model) {
        String mav = null;
        if(roleService.hasAuthority(authentication, "admin").get()){
            mav =  "ingredient/adminIndex";
        }
        else{
            User user =userService.findByUsername(authentication.getName());
            Map<Long, KeyValuePair<Integer, Measurement>> pantryMap = pantryService.getPantry(user.getPantry());
            model.addAttribute("pantryMap", pantryMap);
            System.out.println(pantryMap);
            mav = "ingredient/index";
        }
        return mav;
    }


    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView setUpNutrients(HttpServletRequest request,
                                       @ModelAttribute("measurements") ArrayList<Measurement> measurements,  BindingResult result,
                                       @ModelAttribute("ingredient") Ingredient ingredient,
                                       @ModelAttribute("nutrients") List<Nutrient> nutrients
                                       ) {
        ModelAndView mav = null;
        List<IngredientNutrient> ingredientNutrients = new ArrayList<>();
        for(int i=0; i<nutrients.size(); i++){
            ingredientNutrients.add(new IngredientNutrient(nutrients.get(i)));
        }

        ingredient.setIngredientNutrients(ingredientNutrients);
        mav = new ModelAndView("ingredient/create", "ingredientNutrients", ingredientNutrients);
        return mav;
    }

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public ModelAndView addNutrientsToPage(HttpServletRequest request, @RequestParam ArrayList<Long> chosen, final RedirectAttributes redirectAttributes) {

        ModelAndView mav;
        List<IngredientNutrient> ingredientNutrients = new ArrayList<>();
        for(int i=0; i<chosen.size(); i++){
            ingredientNutrients.add(new IngredientNutrient(nutrientService.get(chosen.get(i))));
        }

        redirectAttributes.addFlashAttribute("ingredientNutrients", ingredientNutrients);
        mav = new ModelAndView("redirect:/ingredient/create2");
        return mav;
    }

    @RequestMapping(value="/create2", method= RequestMethod.GET)
    public ModelAndView createIngredient(HttpServletRequest request,
                                         @ModelAttribute("measurements") ArrayList<Measurement> measurements,  BindingResult result,
                                         @ModelAttribute("ingredient") Ingredient ingredient,
                                          List<IngredientNutrient> ingredientNutrients //this will be handled by the forward request
                                         ) {
        System.out.println("Hello from /create2 get");

        System.out.println(ingredientNutrients);
        ModelAndView mav = null;
//        ArrayList<Nutrient> nutrients = (ArrayList<Nutrient>) nutrientService.findAll();
//        List<IngredientNutrient> ingredientNutrients = new ArrayList<>();
//        for(int i=0; i<nutrients.size(); i++){
//            ingredientNutrients.add(new IngredientNutrient(nutrients.get(i)));
//        }
        //ingredient.setIngredientNutrients(ingredientNutrients);
        mav = new ModelAndView("ingredient/create1", "ingredient", ingredient);
        return mav;
    }

    @RequestMapping(value="/create2", method= RequestMethod.POST)
    public ModelAndView storeIngredient(HttpServletRequest request,
                                        @ModelAttribute("ingredient") Ingredient ingredient,
                                        BindingResult result, @RequestParam FormHelper formHelper
            /* @RequestParam ArrayList<HashMap<String,String>> nutrients*/) {
        // the problem with not having ingredientNutrients already binded to ingredient is that I have to go through the request manually
        // and add it to
        System.out.println(request);
        System.out.println("Hello from /create1 post");
        System.out.println(ingredient);
        System.out.println(formHelper.getNutrientAmounts());
        //System.out.println(nutrients);
        ModelAndView mav = null;
        if(result.hasErrors()){
            mav = new ModelAndView("ingredient/create");
            return mav;

        }
//        ingredient.setMeasurement(measurementService.get(ingredient.getMeasurement().getId()));
//        //the problem is when I get ingredient back from form the list ingredient.ingredientNutrients is filled with
//        // IngredientNutrients that have the amount field filled in but the ingredient and nutrient field are null
//        // cannot fill a object datatype field in html so lets think of another way....
//
//
//        //what if in the form we had a form:hidden which had a path of path="ingredientNutrients[index].nutrient.id
//        // and we set THAT equal to the id of the nutrient during the loop
//
//        //When this comes back from the form the ingredientNutrients is going to be an arraylist where each
//        // IngredientNutrient only has the amount of that nutrient for the ingredient  and the nutrient object type is
//        // going to have an id
//        /*example IngredientNutrient:
//        {
//        id: 0,
//        nutrient: {id: 1, name: null, ingredientNutrients: null}
//        ingredient: null,
//        amount: 10
//        }
//
//        so for each IngredientNutrient we're going to setIngredient to the ingredient and the nutrient to the
//        id inside of the nutrient property (which is really just used to pluck the id)
//         */
//        ingredient.getIngredientNutrients().forEach(ingredientNutrient -> {
//            ingredientNutrient.setIngredient(ingredient);
//            ingredientNutrient.setNutrient(nutrientService.get(ingredientNutrient.getNutrient().getId()));
//        });
//        ingredientService.save(ingredient);

        mav = new ModelAndView("redirect:");
        return mav;
    }




    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ModelAndView viewIngredient(HttpServletRequest request, @PathVariable Long id,
                                       @ModelAttribute("ingredient") Ingredient ingredient) {
        ModelAndView mav = null;
        ingredient = ingredientService.get(id);
        mav = new ModelAndView("ingredient/view");
        mav.addObject(ingredient);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
    public ModelAndView editIngredient(HttpServletRequest request, @PathVariable Long id,
                                       @ModelAttribute("ingredient") Ingredient ingredient,
                                       @ModelAttribute("nutrient") Nutrient nutrient, BindingResult result,
                                       @ModelAttribute("measurements") ArrayList<Measurement> measurements) {
        ModelAndView mav = null;
        ingredient = ingredientService.get(id);
        mav = new ModelAndView("ingredient/edit");
        mav.addObject(ingredient);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.POST)
    public ModelAndView updateIngredient(HttpServletRequest request,  @ModelAttribute("nutrient") Nutrient nutrient) {
        ModelAndView mav = null;
        mav = new ModelAndView("nutrient/edit");
        nutrientService.save(nutrient);
        return mav;
    }
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteNutrient(@PathVariable int id){
        ingredientService.delete(ingredientService.get(id));
        return "redirect:/ingredient/";
    }


    @RequestMapping(value="/example", method = RequestMethod.GET)
    public String example(){
        return "ingredient/exampleForm";
    }

    @RequestMapping(value="/example", method = RequestMethod.POST)
    public String getData(@RequestParam Map<String, String> hashMap){
        System.out.println(hashMap);
        // what I want { a=> 1, b=>2, c=>3, d=>4  }
        return "ingredient/exampleForm";
    }
    @RequestMapping(value="/example2", method = RequestMethod.GET)
    public String example2(){
        return "ingredient/exampleForm2";
    }

}
