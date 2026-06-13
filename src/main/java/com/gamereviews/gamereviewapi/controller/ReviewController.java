package com.gamereviews.gamereviewapi.controller;

import com.gamereviews.gamereviewapi.dto.ReviewRequest;
import com.gamereviews.gamereviewapi.entity.Review;
import org.springframework.web.bind.annotation.*;
import com.gamereviews.gamereviewapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping
    public Review createReview(@RequestBody ReviewRequest review) {

       Review savedReview = reviewService.createReview(review.getUserId(), review.getGameId(), review.getRating(), review.getReviewText());

        return savedReview;

    }

    @GetMapping("/user/{userId}")
    public List<Review> getAllReviewsByUser(@PathVariable Long userId) {

        return reviewService.getReviewsByUser(userId);

    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId, @RequestParam Long userId) {

        String deletedReview = reviewService.deleteReview(reviewId, userId);

        return deletedReview;


    }
}
