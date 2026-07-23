package com.quiz.leaderboard_service.messaging;

import com.quiz.leaderboard_service.event.QuizCompletedEvent;
import com.quiz.leaderboard_service.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuizCompletedConsumer {

    private final LeaderboardService leaderboardService;

    @KafkaListener(
            topics = "quiz-completed",
            groupId = "leaderboard-service"
    )
    public void consumeQuizCompletedEvent(
            QuizCompletedEvent event
    ) {

        log.info(
                "Received quiz completed event: quizId={}, userId={}, score={}",
                event.quizId(),
                event.userId(),
                event.score()
        );

        leaderboardService
                .processQuizCompletedEvent(event);

        log.info(
                "Leaderboard updated for userId={}",
                event.userId()
        );
    }
}