package com.skillbridge.controller;
import com.skillbridge.model.Review;
import com.skillbridge.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @GetMapping("/service/{id}")
    public List<Review> getReviews(@PathVariable Long id) {
        return reviewRepository.findByServiceId(id);
    }
}