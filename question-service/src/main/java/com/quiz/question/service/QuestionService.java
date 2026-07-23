package com.quiz.question.service;

import com.quiz.question.entity.Question;
import com.quiz.question.exception.QuestionNotFoundException;
import com.quiz.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() ->
                        new QuestionNotFoundException("Question not found with id: " + id));
    }

    public List<Question> getByCategory(String category) {
        return questionRepository.findByCategoryIgnoreCase(category);
    }

    public List<Question> getByDifficulty(String difficulty) {
        return questionRepository.findByDifficultyIgnoreCase(difficulty);
    }

    public List<Question> getByCategoryAndDifficulty(
            String category,
            String difficulty
    ) {
        return questionRepository
                .findByCategoryIgnoreCaseAndDifficultyIgnoreCase(
                        category,
                        difficulty
                );
    }

    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException(
                    "Question not found with id: " + id
            );
        }

        questionRepository.deleteById(id);
    }
}