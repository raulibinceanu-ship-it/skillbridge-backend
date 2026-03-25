package com.skillbridge.controller;

import com.skillbridge.model.Review;
import com.skillbridge.repository.ReviewRepository;
import com.skillbridge.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ServiceRepository serviceRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            ServiceRepository serviceRepository) {
        this.reviewRepository = reviewRepository;
        this.serviceRepository = serviceRepository;
    }

    @PostMapping("/{serviceId}")
    public Review createReview(@PathVariable Long serviceId,
                               @RequestBody Review review) {

        review.setService(
                serviceRepository.findById(serviceId).orElseThrow()
        );

        return reviewRepository.save(review);
    }

    @GetMapping("/{serviceId}")
    public List<Review> getReviews(@PathVariable Long serviceId) {
        return reviewRepository.findByServiceId(serviceId);
    }
}