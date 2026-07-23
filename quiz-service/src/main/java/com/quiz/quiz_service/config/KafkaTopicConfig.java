package com.quiz.quiz_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    public static final String QUIZ_COMPLETED_TOPIC =
            "quiz-completed";

    @Bean
    public NewTopic quizCompletedTopic() {

        return new NewTopic(
                QUIZ_COMPLETED_TOPIC,
                3,
                (short) 1
        );
    }
}