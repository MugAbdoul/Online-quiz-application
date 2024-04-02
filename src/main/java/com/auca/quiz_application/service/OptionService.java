package com.auca.quiz_application.service;

import com.auca.quiz_application.model.Option;
import java.util.List;
import java.util.UUID;

public interface OptionService {
    Option getOptionById(UUID id);
    List<Option> getAllOptions();
    List<Option> getOptionsByQuestionId(UUID questionId);
    void createOption(Option option);
    void updateOption(Option option);
    void deleteOption(UUID id);
}
