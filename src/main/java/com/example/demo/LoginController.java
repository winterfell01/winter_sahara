package com.example.demo;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private LoginService service;

    @RequestMapping ("/home")
    public String home(){

        return "home";
    }

    @RequestMapping("/")
    public String index (Model model){

        return "login_form";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(){

        return "login_form";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(@ModelAttribute(name = "loginForm") User user, Model model){
        if(service.findUser(user.getUsername(), user.getPassword())!= null){
            return "home";
        }
        else
        {
            model.addAttribute("invalidCredentials", true);
            return "login_form";
        }
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm(Model model)
    {
        model.addAttribute("user", new User());
        return "register_form";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User userForm, BindingResult result){
        if (result.hasErrors()){
            return "register_form";
        }
        String firstname = userForm.getFirstName();
        String lastname = userForm.getLastName();
        String username = userForm.getUsername();
        String email = userForm.getEmail();
        String password = userForm.getPassword();

        User user = new User (firstname,lastname, username, email, password);

        service.registerUser(user);
        return "login_form";

    }
}
