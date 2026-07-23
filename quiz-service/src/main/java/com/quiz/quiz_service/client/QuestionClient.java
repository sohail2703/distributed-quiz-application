package com.quiz.quiz_service.client;

import com.quiz.quiz_service.dto.QuestionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "question-service")
public interface QuestionClient {

    @GetMapping("/api/questions/{id}")
    QuestionResponse getQuestionById(
            @PathVariable("id") Long id
    );

    @GetMapping("/api/questions")
    List<QuestionResponse> getAllQuestions();
}
