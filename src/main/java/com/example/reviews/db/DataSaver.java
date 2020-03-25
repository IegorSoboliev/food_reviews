package com.example.reviews.db;

import com.example.reviews.model.Review;
import com.example.reviews.model.Role;
import com.example.reviews.model.User;
import com.example.reviews.service.ReviewService;
import com.example.reviews.service.RoleService;
import com.example.reviews.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSaver {
    private CsvReader csvReader;
    private ReviewService reviewService;
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LogManager.getLogger(DataSaver.class);

    public DataSaver(CsvReader csvReader, ReviewService reviewService, UserService userService,
                     RoleService roleService, PasswordEncoder passwordEncoder) {
        this.csvReader = csvReader;
        this.reviewService = reviewService;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void storeData() {
        try {
            LOGGER.info("Starting data reading from CSV file");
            List<Review> data = new ArrayList<>();
            for (CSVRecord record : csvReader.readAll()) {
                Review review = new Review();
                review.setProductId(record.get("ProductId"));
                review.setProfileName(record.get("ProfileName"));
                review.setScore(Integer.parseInt(record.get("Score")));
                review.setText(record.get("Text"));
               data.add(review);
            }
            LOGGER.info("Data from CSV file was read and saved in List<Review>");
            List<Review> fiveThousands = data.stream().limit(5000L).collect(Collectors.toList());
            reviewService.saveAll(fiveThousands);
            LOGGER.info("Data was stored in h2 database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot store data from CSV file in h2 database");
        }
    }

    @EventListener
    public void addData(ContextRefreshedEvent event)  {
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName("USER");
        roleService.save(userRole);

        User user = new User();
        user.setEmail("sofia@yahoo.com");
        user.setPassword(passwordEncoder.encode("1"));
        user.setRole(roleService.getRoleByRoleName("USER"));
        userService.save(user);

        User admin = new User();
        admin.setEmail("pavlo@yahoo.com");
        admin.setPassword(passwordEncoder.encode("1"));
        admin.setRole(roleService.getRoleByRoleName("ADMIN"));
        userService.save(admin);
        LOGGER.info("Roles, admin and user was saved to database");
    }
}
