package com.auca.quiz_application.controller;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.auca.quiz_application.model.User;
import com.auca.quiz_application.service.UserService;
import com.auca.quiz_application.util.Encryptor;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("username") String username,
                         HttpSession session,
                         HttpServletResponse response) {

        // Check if the email is already registered
        if (userService.getUserByEmail(email) != null) {
            return "redirect:/signup?error=email_exists";
        }

        // Create a new user
        User newUser = new User();

        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setrole("user");
        newUser.setPasswordHash(Encryptor.encryptPassword(password));

        try{
            User createdUser = userService.createUser(newUser);
            session.setAttribute("user", createdUser);

            return "redirect:/login";
        }
        catch(Exception e){
            return "redirect:/signup?error=An error occured";
        }

        
    }
}
