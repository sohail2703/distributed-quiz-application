package com.quiz.quiz_service.service;

import com.quiz.quiz_service.client.QuestionClient;
import com.quiz.quiz_service.dto.CreateQuizRequest;
import com.quiz.quiz_service.dto.QuestionResponse;
import com.quiz.quiz_service.dto.SubmitQuizRequest;
import com.quiz.quiz_service.entity.Quiz;
import com.quiz.quiz_service.enums.QuizStatus;
import com.quiz.quiz_service.exception.InvalidQuizStateException;
import com.quiz.quiz_service.exception.QuizNotFoundException;
import com.quiz.quiz_service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    private final QuestionClient questionClient;

    public Quiz createQuiz(CreateQuizRequest request) {

        // Verify that all questions exist
        for (Long questionId : request.questionIds()) {
            questionClient.getQuestionById(questionId);
        }

        Quiz quiz = Quiz.builder()
                .title(request.title())
                .category(request.category())
                .userId(request.userId())
                .questionIds(request.questionIds())
                .totalQuestions(request.questionIds().size())
                .status(QuizStatus.CREATED)
                .score(0)
                .build();

        return quizRepository.save(quiz);
    }

    public Quiz getQuizById(Long id) {

        return quizRepository.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException(
                                "Quiz not found with id: " + id
                        )
                );
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public List<Quiz> getQuizzesByUser(Long userId) {
        return quizRepository.findByUserId(userId);
    }

    public Quiz startQuiz(Long id) {

        Quiz quiz = getQuizById(id);

        if (quiz.getStatus() != QuizStatus.CREATED) {
            throw new InvalidQuizStateException(
                    "Quiz cannot be started because current status is "
                            + quiz.getStatus()
            );
        }

        quiz.setStatus(QuizStatus.IN_PROGRESS);
        quiz.setStartedAt(LocalDateTime.now());

        return quizRepository.save(quiz);
    }

    public Quiz submitQuiz(
            Long id,
            SubmitQuizRequest request
    ) {

        Quiz quiz = getQuizById(id);

        if (quiz.getStatus() != QuizStatus.IN_PROGRESS) {
            throw new InvalidQuizStateException(
                    "Quiz cannot be submitted because current status is "
                            + quiz.getStatus()
            );
        }

        int score = calculateScore(
                quiz.getQuestionIds(),
                request
        );

        quiz.setScore(score);
        quiz.setStatus(QuizStatus.COMPLETED);
        quiz.setCompletedAt(LocalDateTime.now());

        return quizRepository.save(quiz);
    }

    private int calculateScore(
            List<Long> questionIds,
            SubmitQuizRequest request
    ) {

        int score = 0;

        for (Long questionId : questionIds) {

            QuestionResponse question =
                    questionClient.getQuestionById(questionId);

            String submittedAnswer =
                    request.answers().get(questionId);

            if (submittedAnswer != null &&
                    submittedAnswer.equalsIgnoreCase(
                            question.correctAnswer()
                    )) {

                score++;
            }
        }

        return score;
    }

    public void deleteQuiz(Long id) {

        Quiz quiz = getQuizById(id);

        quizRepository.delete(quiz);
    }
}
