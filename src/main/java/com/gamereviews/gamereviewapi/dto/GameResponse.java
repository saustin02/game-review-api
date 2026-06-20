package com.gamereviews.gamereviewapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {

    private Long id;
    private String title;
    private String genre;
    private Integer releaseYear;

}
