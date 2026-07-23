package com.quiz.question.repository;

import com.quiz.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategoryIgnoreCase(String category);

    List<Question> findByDifficultyIgnoreCase(String difficulty);

    List<Question> findByCategoryIgnoreCaseAndDifficultyIgnoreCase(
            String category,
            String difficulty
    );
}