package com.casestudydraft.webcontroller;

import com.casestudydraft.model.Recipe;
import com.casestudydraft.model.User;
import com.casestudydraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @ModelAttribute("users")
    public ArrayList<User> users(){
        List<User> users = userService.findAll();
        return (ArrayList<User>) users;
    }
    @RequestMapping("/")
    public ModelAndView viewAllUsers(HttpServletRequest request, @ModelAttribute("users") ArrayList<User> users) {
        ModelAndView mav = null;
        mav = new ModelAndView("user/index");
        return mav;
    }

}
