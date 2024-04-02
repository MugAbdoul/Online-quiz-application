package com.auca.quiz_application.controller;


import javax.servlet.http.*;

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
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rememberMe", required = false) boolean rememberMe,
                        HttpSession session, // Use HttpSession from jakarta.servlet.http
                        HttpServletResponse response) {

        User user = userService.getUserByEmail(email);
        if (user != null && user.getPasswordHash().equals(Encryptor.encryptPassword(password))) {
            session.setAttribute("user", user);

            if (rememberMe) {
                Cookie cookie = new Cookie("rememberMe", "true");
                cookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookie);
            }
            return "redirect:/home";
        } else {
            return "redirect:/login?error";
        }
    }
}
