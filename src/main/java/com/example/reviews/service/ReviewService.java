package com.example.reviews.service;

import com.example.reviews.model.Review;
import com.example.reviews.model.dto.ProductResponseDto;
import com.example.reviews.model.dto.UserResponseDto;
import com.example.reviews.model.dto.WordResponseDto;

import java.util.List;

public interface ReviewService {
    List<Review> saveAll(List<Review> reviews);
    Review save(Review review);
    List<UserResponseDto> findActiveUsers();
    List<ProductResponseDto> findPopularProducts();
    List<WordResponseDto> findPopularWords(Long amount);
}
