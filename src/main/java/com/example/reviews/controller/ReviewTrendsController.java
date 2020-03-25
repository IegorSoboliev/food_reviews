package com.example.reviews.controller;

import com.example.reviews.model.dto.ProductResponseDto;
import com.example.reviews.model.dto.UserResponseDto;
import com.example.reviews.model.dto.WordResponseDto;
import com.example.reviews.service.ReviewService;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trends")
public class ReviewTrendsController {
    private ReviewService reviewService;

    public ReviewTrendsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/users")
    public List<UserResponseDto> findActiveUsers(@RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "1000") Integer limit) {
        Pageable pageRequest = PageRequest.of(page, limit);
        return reviewService.findActiveUsers(pageRequest);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> findPopularProducts(@RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "1000") Integer limit) {
        Pageable pageRequest = PageRequest.of(page, limit);
        return reviewService.findPopularProducts(pageRequest);
    }

    @GetMapping("/words")
    public List<WordResponseDto> findPopularWords(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "1000") Integer limit) {
        Pageable pageRequest = PageRequest.of(page, limit);
        return reviewService.findPopularWords(limit);
    }
}
