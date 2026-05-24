package com.gamereviews.gamereviewapi.repository;

import com.gamereviews.gamereviewapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByTitle(String title);
    //Game findByGenre(String genre);//
    //Game findByReleaseYear(Long releaseYear);//
}
