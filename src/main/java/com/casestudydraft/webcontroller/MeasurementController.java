package com.casestudydraft.webcontroller;

import com.casestudydraft.model.Measurement;

import com.casestudydraft.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="/measurement")
public class MeasurementController {
    //@Autowired MeasurementService measurementService;
    @Autowired
    MeasurementService measurementService;

    @ModelAttribute("measurements")
    public List<Measurement> measurements(){
        List<Measurement> measurements = measurementService.listAll();
        return measurements;
    }
    @RequestMapping(value="", method= RequestMethod.GET)
    public String redirectToMain(){
        return "redirect:measurement/";
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView viewAllMeasurements(HttpServletRequest request) {
        ModelAndView mav = null;
        mav = new ModelAndView("measurement/index");
        return mav;
    }

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView createMeasurement(HttpServletRequest request) {
        ModelAndView mav = null;
        mav = new ModelAndView("measurement/create");
        mav.addObject("message", "");
        return mav;
    }

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public ModelAndView storeMeasurement(HttpServletRequest request) {
        ModelAndView mav = null;
        System.out.println("Inside storeMeasurement");
        try {
            Measurement m = measurementService.findByName(request.getParameter("measurement_name").toLowerCase());
            if(m==null){
                Measurement measurement = new Measurement(request.getParameter("measurement_name").toLowerCase());
                measurementService.save(measurement);
                mav = new ModelAndView("measurement/create");
                mav.addObject("message", "Successfully added!");
                mav.addObject("type", "success");
            }else {
                mav = new ModelAndView("misc/duplicate");
            }
        }
        catch(Exception e) {
            mav = new ModelAndView("measurement/create");
            mav.addObject("message", "An error occurred");
        }
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
    public ModelAndView editMeasurement(HttpServletRequest request, @PathVariable long id,
                                          @ModelAttribute("measurement") Measurement measurement, BindingResult result) {
        ModelAndView mav = null;
        measurement = measurementService.get(id);
        mav = new ModelAndView("measurement/edit");
        mav.addObject(measurement);
        return mav;
    }
    @RequestMapping(value="/edit/{id}", method= RequestMethod.POST)
    public ModelAndView updateMeasurement(HttpServletRequest request,  @ModelAttribute("measurement") Measurement measurement) {
        ModelAndView mav = null;
        mav = new ModelAndView("measurement/edit");
        measurementService.save(measurement);
        return mav;
    }
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteMeasurement(@PathVariable long id){
        measurementService.delete(measurementService.get(id));
        return "redirect:/measurement/";
    }
}