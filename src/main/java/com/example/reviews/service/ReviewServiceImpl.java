package com.example.reviews.service;

import com.example.reviews.model.Review;
import com.example.reviews.repository.ReviewRepository;

import java.util.List;

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
}
