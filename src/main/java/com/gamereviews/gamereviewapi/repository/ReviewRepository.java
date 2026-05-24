package com.gamereviews.gamereviewapi.repository;

import com.gamereviews.gamereviewapi.entity.Review;
import com.gamereviews.gamereviewapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
}

