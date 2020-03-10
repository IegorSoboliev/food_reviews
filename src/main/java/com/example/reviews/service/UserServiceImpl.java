package com.example.reviews.service;

import com.example.reviews.model.User;
import com.example.reviews.repository.UserRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User save (User user) {
        return userRepository.save(user);
    }
}
