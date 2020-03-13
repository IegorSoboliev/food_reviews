package com.example.reviews.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    @NotNull
    private String productId;
    private Long numberOfReviews;
}
