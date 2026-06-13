package com.gamereviews.gamereviewapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRequest {
    private String title;
    private Integer releaseYear;
    private String genre;
}
