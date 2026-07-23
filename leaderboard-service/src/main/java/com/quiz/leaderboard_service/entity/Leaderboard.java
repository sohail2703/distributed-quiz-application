package com.quiz.leaderboard_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leaderboards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer totalQuizzes;

    @Column(nullable = false)
    private Integer totalScore;

    @Column(nullable = false)
    private Integer totalQuestions;

    @Column(nullable = false)
    private Double averageScore;
}