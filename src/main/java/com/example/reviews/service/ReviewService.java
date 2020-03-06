package com.example.reviews.service;

import com.example.reviews.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> saveAll(List<Review> reviews);
}
