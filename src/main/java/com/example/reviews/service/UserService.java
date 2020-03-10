package com.example.reviews.service;

import com.example.reviews.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByEmail(String email);

    User save(User user);
}
