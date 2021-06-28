package com.casestudydraft.api.controller;

import com.casestudydraft.model.Measurement;
import com.casestudydraft.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//Creates Measurement API endpoints
@CrossOrigin
@RestController
@RequestMapping("api")
public class MeasurementAPIController {

    @Autowired
    MeasurementService measurementService;

    @ModelAttribute("measurements")
    public ArrayList<Measurement> measurements(){
        List<Measurement> measurements = measurementService.listAll();
        return (ArrayList<Measurement>) measurements;
    }

    @RequestMapping(value="/measurements")
    public List<Measurement> viewAllMeasurements(@ModelAttribute("measurements") List<Measurement> measurements) {
        return measurements;
    }

    @RequestMapping(value="/measurement/{id}")
    public Measurement findMeasurement(@PathVariable Long id) {
        return measurementService.get(id);
    }

    @RequestMapping(value="/measurement")
    public Measurement findMatchingMeasurements(@RequestParam String q) {
        return measurementService.findByNameIgnoreCaseContaining(q);
    }
}
