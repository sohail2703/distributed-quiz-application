package com.quiz.quiz_service.messaging;

import com.quiz.quiz_service.config.KafkaTopicConfig;
import com.quiz.quiz_service.event.QuizCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuizEventProducer {

    private final KafkaTemplate<String, QuizCompletedEvent>
            kafkaTemplate;

    public void publishQuizCompletedEvent(
            QuizCompletedEvent event
    ) {

        log.info(
                "Publishing quiz completed event for quizId: {}",
                event.quizId()
        );

        kafkaTemplate.send(
                KafkaTopicConfig.QUIZ_COMPLETED_TOPIC,
                event.quizId().toString(),
                event
        );
    }
}
