package com.auca.quiz_application.service.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.quiz_application.model.Question;
import com.auca.quiz_application.repository.QuestionRepository;
import com.auca.quiz_application.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
    
    private final QuestionRepository questionRepository;
    
    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(UUID id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Question editQuestion(Question question) {
        // Assuming the save method also works for updating existing entities
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(UUID id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> getQuestionsByQuizId(UUID quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}
