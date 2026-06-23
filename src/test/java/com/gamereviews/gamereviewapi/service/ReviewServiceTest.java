package com.gamereviews.gamereviewapi.service;

import com.gamereviews.gamereviewapi.entity.Game;
import com.gamereviews.gamereviewapi.entity.User;
import com.gamereviews.gamereviewapi.exception.ResourceNotFoundException;
import com.gamereviews.gamereviewapi.exception.ValidationException;
import com.gamereviews.gamereviewapi.repository.GameRepository;
import com.gamereviews.gamereviewapi.repository.ReviewRepository;
import com.gamereviews.gamereviewapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import com.gamereviews.gamereviewapi.entity.Review;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void createReview_withInvalidRating_throwsValidationException() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(gameRepository.findById(1L)).thenReturn(Optional.of(new Game()));

        // When / Then
        assertThrows(ValidationException.class, () -> {
            reviewService.createReview(1L, 1L, 7, "Great game");
        });
    }

    @Test
    void createReview_withUnknownUser_throwsResourceNotFoundException() {

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reviewService.createReview(1L, 1L, 5, "Great game");
        });

    }

    @Test
    void createReview_withValidData_returnsSavedReview() {
        // Given
        User user = new User();
        Game game = new Game();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Review result = reviewService.createReview(1L, 1L, 5, "Great game");

        // Then
        assertEquals(5, result.getRating());
        assertEquals("Great game", result.getReviewText());
        assertEquals(user, result.getUser());
        assertEquals(game, result.getGame());
    }
}