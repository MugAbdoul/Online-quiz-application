package com.auca.quiz_application.service;

import java.util.UUID;

import com.auca.quiz_application.model.User;

public interface UserService {
    User createUser(User user);
    User getUserById(UUID userId);
    void updateUser(UUID userId, User user);
}

