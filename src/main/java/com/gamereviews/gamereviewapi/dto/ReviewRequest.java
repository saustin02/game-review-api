package com.gamereviews.gamereviewapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private Long userId;
    private Long gameId;
    private Integer rating;
    private String reviewText;

}
