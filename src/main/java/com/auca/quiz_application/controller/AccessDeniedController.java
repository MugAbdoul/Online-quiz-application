package com.auca.quiz_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {
    @GetMapping("access-denied")
    public String home() {
        return "accessDenied";
    }
    
}
