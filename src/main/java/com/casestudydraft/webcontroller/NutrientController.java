package com.casestudydraft.webcontroller;

import com.casestudydraft.model.Measurement;
import com.casestudydraft.model.Nutrient;
import com.casestudydraft.service.MeasurementService;
import com.casestudydraft.service.NutrientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/nutrient")
public class NutrientController {
    @Autowired NutrientService nutrientService;
    @Autowired MeasurementService measurementService;

    @ModelAttribute("nutrients")
    public ArrayList<Nutrient> nutrients(){
        List<Nutrient> nutrients = nutrientService.findAll();
        return (ArrayList<Nutrient>) nutrients;
    }

    @ModelAttribute("measurements")
    public ArrayList<Measurement> measurements(){
        return (ArrayList<Measurement>) measurementService.listAll();
    }


    @RequestMapping(value="", method= RequestMethod.GET)
    public String redirectToMain(){
        return "redirect:nutrient/";
    }
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView viewAllNutrients(HttpServletRequest request){
        ModelAndView mav = null;
        mav = new ModelAndView("nutrient/index");
        return mav;
    }
    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView createNutrient(@ModelAttribute("measurements") ArrayList<Measurement> measurements,
                                       @ModelAttribute("nutrient") Nutrient nutrient
                                       ){
        ModelAndView mav = null;
        mav = new ModelAndView("nutrient/create");
        return mav;
    }

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String storeNutrient(HttpServletRequest request,  @ModelAttribute("nutrient") Nutrient nutrient, BindingResult result, Model model) {
        String mav = null;
        try {
            Nutrient nutrientInDB = nutrientService.findByName(nutrient.getName());
            if(nutrientInDB==null){
                nutrient.setName(nutrient.getName().toLowerCase());
                nutrientService.save(nutrient);
                mav = "redirect:";
                model.addAttribute("message", "Successfully added!");
            }else {
                mav = "misc/duplicate";
            }
        }catch (Exception ex) {
                System.out.println(ex);
        }
        return mav;
    }



    @RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
    public ModelAndView editNutrient(HttpServletRequest request, @PathVariable Long id,
                                     @ModelAttribute("nutrient") Nutrient nutrient, BindingResult result,
                                     @ModelAttribute("measurements") ArrayList<Measurement> measurements) {
        ModelAndView mav = null;
        nutrient = nutrientService.get(id);
        mav = new ModelAndView("nutrient/edit");
        mav.addObject(nutrient);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.POST)
    public ModelAndView updateNutrient(HttpServletRequest request,  @ModelAttribute("nutrient") Nutrient nutrient) {
        ModelAndView mav = null;
        System.out.println(nutrient);
        mav = new ModelAndView("nutrient/edit");
        nutrientService.save(nutrient);
        return mav;
    }
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteNutrient(@PathVariable Long id){
        nutrientService.delete(nutrientService.get(id));
        return "redirect:/nutrient/";
    }
}
