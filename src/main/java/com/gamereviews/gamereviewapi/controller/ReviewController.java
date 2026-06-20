package com.gamereviews.gamereviewapi.controller;

import com.gamereviews.gamereviewapi.dto.GameResponse;
import com.gamereviews.gamereviewapi.dto.ReviewRequest;
import com.gamereviews.gamereviewapi.dto.ReviewResponse;
import com.gamereviews.gamereviewapi.dto.ReviewUserResponse;
import com.gamereviews.gamereviewapi.entity.Game;
import com.gamereviews.gamereviewapi.entity.Review;
import com.gamereviews.gamereviewapi.entity.User;
import org.springframework.web.bind.annotation.*;
import com.gamereviews.gamereviewapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<ReviewResponse> getAllReviews() {

        List<ReviewResponse> reviewResponseList = new ArrayList<>();
        for (Review review : reviewService.getAllReviews()) {
            reviewResponseList.add(toReviewResponse(review));
        }
        return reviewResponseList;
    }



    @PostMapping
    public ReviewResponse createReview(@RequestBody ReviewRequest review) {
       Review savedReview = reviewService.createReview(review.getUserId(), review.getGameId(), review.getRating(), review.getReviewText());
        return toReviewResponse(savedReview);

    }

    @GetMapping("/user/{userId}")
    public List<ReviewResponse> getAllReviewsByUser(@PathVariable Long userId) {
        List<ReviewResponse> reviewsByUserResponseList = new ArrayList<>();
        for (Review review : reviewService.getReviewsByUser(userId)) {
            reviewsByUserResponseList.add(toReviewResponse(review));
        }
        return reviewsByUserResponseList;

    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId, @RequestParam Long userId) {

        String deletedReview = reviewService.deleteReview(reviewId, userId);

        return deletedReview;

    }

    private ReviewUserResponse toReviewUserResponse(User user) {
        return new ReviewUserResponse(
                user.getId(),
                user.getUsername()
        );
    }

    private GameResponse toGameResponse(Game game) {
        return new GameResponse(
                game.getId(),
                game.getTitle(),
                game.getGenre(),
                game.getReleaseYear()
        );
    }


    private ReviewResponse toReviewResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                toReviewUserResponse(review.getUser()),
                toGameResponse(review.getGame()),
                review.getRating(),
                review.getReviewText(),
                review.getCreatedAt()
        );
    }

}
