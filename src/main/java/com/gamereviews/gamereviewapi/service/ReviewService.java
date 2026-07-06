package com.gamereviews.gamereviewapi.service;

import com.gamereviews.gamereviewapi.entity.User;
import com.gamereviews.gamereviewapi.exception.UnauthorizedException;
import com.gamereviews.gamereviewapi.repository.UserRepository;
import com.gamereviews.gamereviewapi.entity.Game;
import com.gamereviews.gamereviewapi.repository.GameRepository;
import com.gamereviews.gamereviewapi.entity.Review;
import com.gamereviews.gamereviewapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gamereviews.gamereviewapi.exception.ResourceNotFoundException;
import com.gamereviews.gamereviewapi.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    public Review createReview(Long userId, Long gameId, Integer rating, String reviewText) {

        // Validation
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null) {
            throw new ResourceNotFoundException("Game not found");
        }
        if (reviewText == null) {
            throw new ValidationException("You need to write a review!");
        }
        if (rating == null || rating < 1 || rating > 5) {
            throw new ValidationException("Invalid rating!");
        }

        // Create and save review
        Review review = new Review();
        review.setUser(user);
        review.setGame(game);
        review.setRating(rating);
        review.setReviewText(reviewText);

        return reviewRepository.save(review);

    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

        public List<Review> getReviewsByUser(Long userId) {

            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
            throw new ResourceNotFoundException("User does not exist");
            }
            return reviewRepository.findByUser(user);

        }

    public String deleteReview(Long reviewId, Long userId) {

        User user = userRepository.findById(userId).orElse(null);
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null || user == null) {
            throw new ResourceNotFoundException("Review id or user id is invalid!");
        }

        if (!review.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You do not have permission to delete review!");
        }
        reviewRepository.delete(review);
        return "Review deleted";

    }

    public Review updateReview(Long reviewId, Long userId, Integer rating, String reviewText) {

        Review review = reviewRepository.findById(reviewId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (review == null || user == null) {
            throw new ResourceNotFoundException("Review or user id is invalid!");
        }
        if (!review.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You do not have permission to update review!");
        }
        if (rating == null || rating < 1 || rating > 5) {
            throw new ValidationException("Invalid rating!");
        }
        if (reviewText == null) {
            throw new ValidationException("You need to write a review!");
        }

        review.setRating(rating);
        review.setReviewText(reviewText);
        return reviewRepository.save(review);

    }






}
