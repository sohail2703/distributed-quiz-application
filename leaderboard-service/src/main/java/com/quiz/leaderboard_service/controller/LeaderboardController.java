package com.quiz.leaderboard_service.controller;

import com.quiz.leaderboard_service.entity.Leaderboard;
import com.quiz.leaderboard_service.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboards")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping
    public List<Leaderboard> getLeaderboard() {

        return leaderboardService.getLeaderboard();
    }

    @GetMapping("/user/{userId}")
    public Leaderboard getUserLeaderboard(
            @PathVariable Long userId
    ) {

        return leaderboardService
                .getUserLeaderboard(userId);
    }
}