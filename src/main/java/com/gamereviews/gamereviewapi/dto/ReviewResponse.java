package com.gamereviews.gamereviewapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private ReviewUserResponse user;
    private GameResponse game;
    private Integer rating;
    private String reviewText;
    private LocalDateTime createdAt;

}
