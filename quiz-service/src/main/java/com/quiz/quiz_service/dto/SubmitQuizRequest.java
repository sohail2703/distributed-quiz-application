package com.quiz.quiz_service.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.Map;

public record SubmitQuizRequest(

        @NotEmpty
        Map<Long, String> answers
) {
}