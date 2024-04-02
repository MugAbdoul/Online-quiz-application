package com.auca.quiz_application.service.implementation;

import com.auca.quiz_application.model.Option;
import com.auca.quiz_application.repository.OptionRepository;
import com.auca.quiz_application.service.OptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public Option getOptionById(UUID id) {
        return optionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    @Override
    public List<Option> getOptionsByQuestionId(UUID questionId) {
        return null;
    }

    @Override
    public void createOption(Option option) {
        optionRepository.save(option);
    }

    @Override
    public void updateOption(Option option) {
        optionRepository.save(option);
    }

    @Override
    public void deleteOption(UUID id) {
        optionRepository.deleteById(id);
    }
}
