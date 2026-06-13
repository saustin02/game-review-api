package com.gamereviews.gamereviewapi.controller;

import com.gamereviews.gamereviewapi.dto.GameRequest;
import com.gamereviews.gamereviewapi.service.GameService;
import com.gamereviews.gamereviewapi.entity.Game;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public Game createGame(@RequestBody GameRequest gameRequest) {
        Game savedGame = gameService.createGame(gameRequest.getTitle(), gameRequest.getReleaseYear(), gameRequest.getGenre());
        return savedGame;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }
}
