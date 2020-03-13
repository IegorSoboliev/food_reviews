package com.example.reviews.service;

import com.example.reviews.model.Review;
import com.example.reviews.model.dto.ProductResponseDto;
import com.example.reviews.model.dto.UserResponseDto;
import com.example.reviews.model.dto.WordResponseDto;
import com.example.reviews.repository.ReviewRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> saveAll(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<UserResponseDto> findActiveUsers() {
        return reviewRepository.findActiveUsers();
    }

    @Override
    public List<ProductResponseDto> findPopularProducts() {
        return reviewRepository.findPopularProducts();
    }

    @Override
    public List<WordResponseDto> findPopularWords(Long amount) {
        return reviewRepository
                .findPopularWords()
                .stream()
                .map(s -> s.replaceAll("[^a-zA-z0-9]", " ").toLowerCase().split(" "))
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(amount)
                .map(Entry-> new WordResponseDto(Entry.getKey(), Entry.getValue()))
                .collect(Collectors.toList());
    }
}
