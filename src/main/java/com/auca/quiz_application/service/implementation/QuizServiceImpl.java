package com.auca.quiz_application.service.implementation;

import com.auca.quiz_application.model.Quiz;
import com.auca.quiz_application.repository.QuizRepository;
import com.auca.quiz_application.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(UUID id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(UUID id) {
        quizRepository.deleteById(id);
    }
}
