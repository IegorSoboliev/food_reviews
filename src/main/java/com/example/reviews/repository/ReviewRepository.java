package com.example.reviews.repository;

import com.example.reviews.model.Review;
import com.example.reviews.model.dto.ProductResponseDto;
import com.example.reviews.model.dto.UserResponseDto;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT new com.example.reviews.model.dto.UserResponseDto (r.profileName, "
            + "COUNT (r.text)) FROM Review AS r GROUP BY r.profileName, r.text ORDER BY COUNT (r.text) DESC")
    List<UserResponseDto> findActiveUsers(Pageable pageable);

    @Query(value = "SELECT new com.example.reviews.model.dto.ProductResponseDto (r.productId, COUNT"
            + "(r.productId)) FROM Review AS r GROUP BY r.productId ORDER BY COUNT (r.productId) DESC")
    List<ProductResponseDto> findPopularProducts(Pageable pageable);

    @Query(value = "SELECT new java.lang.String (r.text) FROM Review AS r")
    List<String> findPopularWords();
}

