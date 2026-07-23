package com.quiz.quiz_service.event;

public record QuizCompletedEvent(

        Long quizId,

        Long userId,

        String quizTitle,

        String category,

        Integer score,

        Integer totalQuestions
) {
}
