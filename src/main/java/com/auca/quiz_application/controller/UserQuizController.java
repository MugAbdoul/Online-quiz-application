package com.auca.quiz_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.auca.quiz_application.service.QuizService;

@Controller
public class UserQuizController {
    private final QuizService quizService;

    @Autowired
    public UserQuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/user-home")
    public String dashboard(Model model) {
        // Fetch all quizzes
        model.addAttribute("quizzes", quizService.getAllQuizzes());
        return "userquiz";
    }
}
