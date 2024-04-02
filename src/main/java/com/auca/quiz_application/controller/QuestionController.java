package com.auca.quiz_application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.auca.quiz_application.model.Quiz;
import com.auca.quiz_application.model.Question;
import com.auca.quiz_application.service.QuestionService;
import com.auca.quiz_application.service.QuizService;

@Controller
public class QuestionController {
    
    private final QuizService quizService;
    private final QuestionService questionService;
    
    @Autowired
    public QuestionController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping("/quiz/{id}/questions")
    public String showQuestionsForQuiz(@PathVariable("id") UUID quizId, Model model) {
        // Retrieve the quiz by ID
        Quiz quiz = quizService.getQuizById(quizId);
        if (quiz == null) {
            // If the quiz is not found, handle this accordingly
            // For example, you can redirect to an error page or return an error message
            return "redirect:/error"; // Assuming you have an error page mapped to /error
        }
        
        // Retrieve the questions for the specified quiz ID
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        
        // Add the quiz and questions to the model
        model.addAttribute("quizId", quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        
        // Render the adminQuestion view
        return "adminQuestion";
    }

    @PostMapping("/quiz/{id}/add")
    public String addQuestionToQuiz(
            @PathVariable("id") UUID quizId,
            @RequestParam("title") String title,
            RedirectAttributes redirectAttributes) {
        // Create the new question
        Quiz quiz = quizService.getQuizById(quizId);

        Question newQuestion = new Question();
        newQuestion.setText(title);
        newQuestion.setQuiz(quiz);
        questionService.createQuestion(newQuestion);
        return "redirect:/quiz/" + quizId + "/questions";
    }

    @PostMapping("/quiz/{quizId}/question/{questionId}/edit")
    public String updateQuestion(
            @PathVariable("quizId") UUID quizId,
            @PathVariable("questionId") UUID questionId,
            @RequestParam("title") String title,
            RedirectAttributes redirectAttributes) {
        // Retrieve the question by ID
        Question question = questionService.getQuestionById(questionId);
        if (question == null) {
            // If the question is not found, handle this accordingly
            // For example, you can redirect to an error page or return an error message
            return "redirect:/error"; // Assuming you have an error page mapped to /error
        }

        // Update the question title
        question.setText(title);
        questionService.editQuestion(question);

        // Redirect back to the questions page for the quiz
        return "redirect:/quiz/" + quizId + "/questions";
    }

    @GetMapping("/quiz/{quizId}/question/{questionId}/delete")
    public String deleteQuestion(
            @PathVariable("quizId") UUID quizId,
            @PathVariable("questionId") UUID questionId) {
        // Retrieve the question by ID
        Question question = questionService.getQuestionById(questionId);
        if (question == null) {
            return "redirect:/error";
        }

        questionService.deleteQuestion(questionId);
        return "redirect:/quiz/" + quizId + "/questions";
    }

}
