package com.example.reviews;

import com.example.reviews.db.CsvReader;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewsApplication.class, args);
        try {
            CsvReader csvReader = new CsvReader();
            csvReader.readAll();
            System.out.println("Data was read");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find file", e);
        }
    }
}
