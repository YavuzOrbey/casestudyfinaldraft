package com.casestudydraft.webcontroller;

import com.casestudydraft.model.Ingredient;
import com.casestudydraft.model.Measurement;
import com.casestudydraft.model.Recipe;
import com.casestudydraft.model.Role;
import com.casestudydraft.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
@RequestMapping("role")
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView createRoleView(){
        ModelAndView mav = null;
        mav = new ModelAndView("role/create");
        mav.addObject("role", new Role());
        return mav;
    }

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String createRole(@ModelAttribute("role") Role role){
        roleService.save(role);
        return "redirect:";
    }

    /*One Time give all users admin privilege */
    @RequestMapping(value="/createadmin", method= RequestMethod.GET)
    public void createAdmin(){
        roleService.createAdmin();
        roleService.setAdmin();
    }
}
