package com.auca.quiz_application.service.implementation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.quiz_application.model.User;
import com.auca.quiz_application.repository.UserRepository;
import com.auca.quiz_application.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }


    @Override
    public void updateUser(UUID userId, User user) {
        userRepository.save(user);
    }
}
 
