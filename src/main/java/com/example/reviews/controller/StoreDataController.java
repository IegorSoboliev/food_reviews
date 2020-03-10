package com.example.reviews.controller;

import com.example.reviews.db.CsvReader;
import com.example.reviews.model.Review;
import com.example.reviews.service.ReviewService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class StoreDataController {
    private CsvReader csvReader;
    private ReviewService reviewService;
    private final Logger LOGGER = LogManager.getLogger(StoreDataController.class);

    public StoreDataController(CsvReader csvReader, ReviewService reviewService) {
        this.csvReader = csvReader;
        this.reviewService = reviewService;
    }

    @EventListener
    public String storeData(ContextStartedEvent event) {
        try {
            LOGGER.info("Starting look for data from CSV file");
            List<Review> data = new ArrayList<>();
            for (CSVRecord record : csvReader.readAll()) {
                Review review = new Review();
                review.setProductId(Long.parseLong(record.get("ProductId")));
                review.setProfileName(record.get("ProfileName"));
                review.setScore(Integer.parseInt(record.get("Score")));
                review.setText(record.get("Text"));
                data.add(review);
            }
            LOGGER.info("Read data from CSV file");
            reviewService.saveAll(data);
            return "Data was stored in h2 database";
        } catch (IOException e) {
            throw new RuntimeException("Cannot store data from CSV file in h2 database");
        }
    }
}
