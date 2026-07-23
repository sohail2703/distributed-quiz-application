package com.quiz.quiz_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateQuizRequest(

        @NotBlank
        String title,

        @NotBlank
        String category,

        @NotNull
        Long userId,

        @NotEmpty
        List<Long> questionIds
) {
}
