package com.quiz.quiz_service.dto;

public record QuestionResponse(

        Long id,

        String questionText,

        String optionA,

        String optionB,

        String optionC,

        String optionD,

        String correctAnswer,

        String category,

        String difficulty
) {
}