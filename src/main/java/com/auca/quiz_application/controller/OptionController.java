package com.auca.quiz_application.controller;

import com.auca.quiz_application.model.Option;
import com.auca.quiz_application.model.Question;
import com.auca.quiz_application.service.OptionService;
import com.auca.quiz_application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class OptionController {

    private final OptionService optionService;
    private final QuestionService questionService;

    @Autowired
    public OptionController(OptionService optionService, QuestionService questionService) {
        this.optionService = optionService;
        this.questionService = questionService;
    }

    @PostMapping("/quiz/{quizId}/question/{questionId}/add")
    public String addOptionToQuestion(
            @RequestParam("title") String title,
            @RequestParam(value = "isCorrect", required = false) boolean isCorrect,
            @ModelAttribute("quizId") UUID quizId,
            @ModelAttribute("questionId") UUID questionId,
            RedirectAttributes redirectAttributes) {

        Question question = questionService.getQuestionById(questionId);
        
        if (question != null) {
            Option option = new Option();
            option.setText(title);
            option.setCorrect(isCorrect);
            option.setQuestion(question);
            
            optionService.createOption(option);
            redirectAttributes.addFlashAttribute("message", "Option added successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Question not found.");
        }

        return "redirect:/quiz/" + quizId + "/questions";
    }
}
