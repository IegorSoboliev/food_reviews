package com.example.reviews.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String productId;
    private String profileName;
    private int score;
    @Column (columnDefinition = "TEXT")
    private String text;
}
