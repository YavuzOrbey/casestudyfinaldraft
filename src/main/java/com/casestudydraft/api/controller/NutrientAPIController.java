package com.casestudydraft.api.controller;

import com.casestudydraft.model.Nutrient;
import com.casestudydraft.service.NutrientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("api")
public class NutrientAPIController {
    @Autowired
    NutrientService nutrientService;

    @RequestMapping(value="/nutrients")
    public List<Nutrient> viewAllNutrients(@ModelAttribute("nutrients") List<Nutrient> nutrients) {
        return nutrients;
    }

    @RequestMapping(value="/nutrient/{id}")
    public Nutrient findNutrient(@PathVariable Long id) {
        return nutrientService.get(id);
    }

    @RequestMapping(value="/nutrient")
    public List<Nutrient> findMatchingNutrients(@RequestParam String q) {
        return nutrientService.findByNameIgnoreCaseContaining(q);
    }
}
