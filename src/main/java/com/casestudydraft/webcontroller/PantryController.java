package com.casestudydraft.webcontroller;

import com.casestudydraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pantry")
public class PantryController {
    //Pantry is going to be a little different we won't be adding a new pantry so look at the times we edit a ingredient/recipe instead
    @Autowired
    UserService userService;
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView myPantry(Model model){

        //find current user
        model.addAttribute(userService.findByUsername("yavuz"));
        System.out.println(model);
        ModelAndView mav = null;
        return mav;
    }
}
