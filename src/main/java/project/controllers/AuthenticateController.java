package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import project.exceptions.DuplicateUserException;
import project.services.AuthenticateService;
import project.services.UserService;

@Controller
public class AuthenticateController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("partial/auth/register", "model", new AuthenticateService.Form());
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public View doRegister(AuthenticateService.Form form){
        try{
            long id = userService.register(form.getUsername(), form.getPassword());
            return new RedirectView("/login", true);
        }catch (DuplicateUserException due){
            String message = due.getMessage();
            return new RedirectView("/register", true);
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("partial/auth/login", "model", new AuthenticateService.Form());
    }
}
