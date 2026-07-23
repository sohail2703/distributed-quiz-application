package com.quiz.question.controller;

import com.quiz.question.entity.Question;
import com.quiz.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question createQuestion(
            @Valid @RequestBody Question question
    ) {
        return questionService.createQuestion(question);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(
            @PathVariable Long id
    ) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/category/{category}")
    public List<Question> getByCategory(
            @PathVariable String category
    ) {
        return questionService.getByCategory(category);
    }

    @GetMapping("/difficulty/{difficulty}")
    public List<Question> getByDifficulty(
            @PathVariable String difficulty
    ) {
        return questionService.getByDifficulty(difficulty);
    }

    @GetMapping("/filter")
    public List<Question> getByCategoryAndDifficulty(
            @RequestParam String category,
            @RequestParam String difficulty
    ) {
        return questionService.getByCategoryAndDifficulty(
                category,
                difficulty
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(
            @PathVariable Long id
    ) {
        questionService.deleteQuestion(id);
    }
}