package com.gamereviews.gamereviewapi.service;

import com.gamereviews.gamereviewapi.exception.ValidationException;
import com.gamereviews.gamereviewapi.repository.GameRepository;
import com.gamereviews.gamereviewapi.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game createGame(String title, Integer releaseYear, String genre) {

        if (title == null || title.isEmpty()) {
            throw new ValidationException("title is empty!");
        }
        if (gameRepository.findByTitle(title) != null) {
            throw new ValidationException("title already exists!");
        }
        if (releaseYear == null || releaseYear < 0 || releaseYear > LocalDate.now().getYear()) {
            throw new ValidationException("invalid release year!");
        }
        if (genre == null || genre.isEmpty()) {
            throw new ValidationException("genre is empty!");
        }

        Game game = new Game();
        game.setTitle(title);
        game.setReleaseYear(releaseYear);
        game.setGenre(genre);

        return gameRepository.save(game);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

}
