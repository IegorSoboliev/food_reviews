package com.example.reviews.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserResponseDto {
    @NotNull
    private String profileName;
    @NotNull
    private int numberOfReviews;
}
