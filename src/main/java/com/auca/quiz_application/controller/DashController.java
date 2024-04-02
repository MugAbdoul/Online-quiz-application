package com.auca.quiz_application.controller;

import com.auca.quiz_application.model.Quiz;
import com.auca.quiz_application.service.QuizService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashController {

    private final QuizService quizService;

    @Autowired
    public DashController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Fetch all quizzes
        model.addAttribute("quizzes", quizService.getAllQuizzes());
        return "adminQuiz";
    }
    

    @PostMapping("/dashboard")
    public String createQuiz(@ModelAttribute Quiz quiz) {
        // Save the new quiz
        quizService.createQuiz(quiz);
        // Redirect back to the admin dashboard
        return "redirect:/dashboard";
    }

    @GetMapping("/quiz/{id}/delete")
    public String deleteQuiz(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        quizService.deleteQuiz(id);
        redirectAttributes.addFlashAttribute("message", "Quiz deleted successfully.");
        return "redirect:/dashboard";
    }

    @PostMapping("/quiz/{id}/edit")
    public String editQuiz(@PathVariable("id") UUID id, @ModelAttribute Quiz updatedQuiz, RedirectAttributes redirectAttributes) {
        // Retrieve the existing quiz from the database
        Quiz existingQuiz = quizService.getQuizById(id);
        if (existingQuiz == null) {
            // Handle error: quiz not found
            redirectAttributes.addFlashAttribute("error", "Quiz not found.");
            return "redirect:/dashboard";
        }

        // Update the existing quiz with the values from the submitted form
        existingQuiz.setTitle(updatedQuiz.getTitle());
        existingQuiz.setDescription(updatedQuiz.getDescription());
        existingQuiz.setDurationInMinutes(updatedQuiz.getDurationInMinutes());

        // Save the updated quiz
        quizService.createQuiz(existingQuiz);

        // Redirect back to the admin dashboard
        redirectAttributes.addFlashAttribute("message", "Quiz updated successfully.");
        return "redirect:/dashboard";
    }



}
