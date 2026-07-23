package com.quiz.quiz_service.repository;

import com.quiz.quiz_service.entity.Quiz;
import com.quiz.quiz_service.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByUserId(Long userId);

    List<Quiz> findByCategoryIgnoreCase(String category);

    List<Quiz> findByStatus(QuizStatus status);
}