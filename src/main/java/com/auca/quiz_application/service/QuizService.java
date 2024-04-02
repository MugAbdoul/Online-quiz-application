package com.auca.quiz_application.service;

import com.auca.quiz_application.model.Quiz;

import java.util.List;
import java.util.UUID;

public interface QuizService {
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(UUID id);
    Quiz createQuiz(Quiz quiz);
    void deleteQuiz(UUID id);
}
