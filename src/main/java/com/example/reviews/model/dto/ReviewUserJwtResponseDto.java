package com.example.reviews.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReviewUserJwtResponseDto implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private String jwtToken;

    public ReviewUserJwtResponseDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
