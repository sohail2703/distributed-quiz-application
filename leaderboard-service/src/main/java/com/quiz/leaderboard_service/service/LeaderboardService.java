package com.quiz.leaderboard_service.service;

import com.quiz.leaderboard_service.entity.Leaderboard;
import com.quiz.leaderboard_service.event.QuizCompletedEvent;
import com.quiz.leaderboard_service.repository.LeaderboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    public void processQuizCompletedEvent(
            QuizCompletedEvent event
    ) {

        Leaderboard leaderboard =
                leaderboardRepository
                        .findByUserId(event.userId())
                        .orElseGet(() ->
                                Leaderboard.builder()
                                        .userId(event.userId())
                                        .totalQuizzes(0)
                                        .totalScore(0)
                                        .totalQuestions(0)
                                        .averageScore(0.0)
                                        .build()
                        );

        leaderboard.setTotalQuizzes(
                leaderboard.getTotalQuizzes() + 1
        );

        leaderboard.setTotalScore(
                leaderboard.getTotalScore()
                        + event.score()
        );

        leaderboard.setTotalQuestions(
                leaderboard.getTotalQuestions()
                        + event.totalQuestions()
        );

        double averageScore =
                ((double) leaderboard.getTotalScore()
                        / leaderboard.getTotalQuestions())
                        * 100;

        leaderboard.setAverageScore(
                Math.round(averageScore * 100.0) / 100.0
        );

        leaderboardRepository.save(leaderboard);
    }

    public List<Leaderboard> getLeaderboard() {
        return leaderboardRepository.findAll();
    }

    public Leaderboard getUserLeaderboard(
            Long userId
    ) {

        return leaderboardRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leaderboard not found for user: "
                                        + userId
                        )
                );
    }
}