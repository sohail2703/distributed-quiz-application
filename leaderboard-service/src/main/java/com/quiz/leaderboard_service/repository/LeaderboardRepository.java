package com.quiz.leaderboard_service.repository;

import com.quiz.leaderboard_service.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaderboardRepository
        extends JpaRepository<Leaderboard, Long> {

    Optional<Leaderboard> findByUserId(Long userId);
}