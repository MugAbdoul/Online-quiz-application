package com.auca.quiz_application.service;

import java.util.List;
import java.util.UUID;

import com.auca.quiz_application.model.Question;

public interface QuestionService {
    Question createQuestion(Question question);
    void deleteQuestion(UUID id);
    Question editQuestion(Question question);
    Question getQuestionById(UUID id);
    List<Question> getQuestionsByQuizId(UUID quizId);
}
