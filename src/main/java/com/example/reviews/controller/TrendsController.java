package com.example.reviews.controller;

import com.example.reviews.model.dto.ProductResponseDto;
import com.example.reviews.model.dto.UserResponseDto;
import com.example.reviews.model.dto.WordResponseDto;
import com.example.reviews.service.ReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trends")
public class TrendsController {
    private ReviewService reviewService;

    public TrendsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/users")
    public List<UserResponseDto> findActiveUsers() {
        return reviewService.findActiveUsers();
    }

    @GetMapping("/products")
    public List<ProductResponseDto> findPopularProducts() {
        return reviewService.findPopularProducts();
    }

    @GetMapping("/words")
    public List<WordResponseDto> findPopularWords() {
        return reviewService.findPopularWords(1000L);
    }
}
