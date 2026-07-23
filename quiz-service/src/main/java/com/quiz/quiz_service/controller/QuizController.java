package com.quiz.quiz_service.controller;

import com.quiz.quiz_service.dto.CreateQuizRequest;
import com.quiz.quiz_service.dto.SubmitQuizRequest;
import com.quiz.quiz_service.entity.Quiz;
import com.quiz.quiz_service.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz createQuiz(
            @Valid @RequestBody CreateQuizRequest request
    ) {
        return quizService.createQuiz(request);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(
            @PathVariable Long id
    ) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Quiz> getQuizzesByUser(
            @PathVariable Long userId
    ) {
        return quizService.getQuizzesByUser(userId);
    }

    @PutMapping("/{id}/start")
    public Quiz startQuiz(
            @PathVariable Long id
    ) {
        return quizService.startQuiz(id);
    }

    @PostMapping("/{id}/submit")
    public Quiz submitQuiz(
            @PathVariable Long id,
            @Valid @RequestBody SubmitQuizRequest request
    ) {
        return quizService.submitQuiz(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(
            @PathVariable Long id
    ) {
        quizService.deleteQuiz(id);
    }
}
