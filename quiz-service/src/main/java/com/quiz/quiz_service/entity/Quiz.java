package com.quiz.quiz_service.entity;

import com.quiz.quiz_service.enums.QuizStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuizStatus status;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer totalQuestions;

    private Integer score;

    private LocalDateTime createdAt;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    @ElementCollection
    @CollectionTable(
            name = "quiz_question_ids",
            joinColumns = @JoinColumn(name = "quiz_id")
    )
    @Column(name = "question_id")
    @Builder.Default
    private List<Long> questionIds = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = QuizStatus.CREATED;
        }

        if (score == null) {
            score = 0;
        }

        if (questionIds == null) {
            questionIds = new ArrayList<>();
        }

        totalQuestions = questionIds.size();
    }
}
