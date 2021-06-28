package com.casestudydraft.webcontroller;

import com.casestudydraft.model.Pantry;
import com.casestudydraft.model.User;
import com.casestudydraft.service.PantryService;
import com.casestudydraft.service.RoleService;
import com.casestudydraft.service.SecurityService;
import com.casestudydraft.service.UserService;
import com.casestudydraft.tools.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;


@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PantryService pantryService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "misc/accessDenied";
    }
    @GetMapping("/register")
    public String registration(@ModelAttribute("userForm") User user) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        userForm.setPantry(new Pantry());
        userService.save(userForm);
        if(userForm.getId()==1){
            //this is the first user so make him an admin
            roleService.createAdmin();
            roleService.setAdmin();
        }
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout, @ModelAttribute("user") User user) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "auth/login";
    }


    @GetMapping("/")
    public String welcome() {
        return "redirect:home";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("pages/landing");
        return mav;
    }


    @RequestMapping(value="/dashboard", method= RequestMethod.GET)
    public ModelAndView showDashboard(HttpServletRequest request) {


        ModelAndView mav = null;

        //get current month
//		Calendar theCalendar = new GregorianCalendar();
//		theCalendar.setTime(new Date());
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        DayOfWeek firstDay = firstDayOfMonth.getDayOfWeek();

        Calendar calendar = Calendar.getInstance();
        int lastDayNum = calendar.getActualMaximum(Calendar.DATE);

        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(lastDayNum);
        DayOfWeek lastDay = lastDayOfMonth.getDayOfWeek();


        int numWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        mav = new ModelAndView("pages/dashboard");

//		Map<Integer, DayOfWeek> theMonth = new HashMap<Integer, DayOfWeek>();
//		for(int i=1; i<= firstDayOfMonth.lengthOfMonth(); i++) {
//			theMonth.put(i, firstDayOfMonth.plusDays(i-1).getDayOfWeek());
//		}

        String[] daysOfWeek = new String[DayOfWeek.values().length];
        for(int i=0; i<  DayOfWeek.values().length; i++){
            daysOfWeek[i] = DayOfWeek.values()[i].getDisplayName(TextStyle.FULL, Locale.US);
        }
        mav.addObject("month_name", firstDayOfMonth.getMonth().name());
        mav.addObject("daysOfWeek", daysOfWeek);
        mav.addObject("numWeeks", numWeeks);
        mav.addObject("firstDay", firstDay.getValue()+1);
        mav.addObject("lastDay", lastDay.getValue()+1);
        return mav;
    }


}