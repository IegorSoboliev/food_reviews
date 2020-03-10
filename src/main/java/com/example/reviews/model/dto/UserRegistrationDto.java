package com.example.reviews.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto {
    @NotNull
    @Size(min = 10)
    private String email;
    @NotNull
    @Size(min = 8)
    private String password;
}
