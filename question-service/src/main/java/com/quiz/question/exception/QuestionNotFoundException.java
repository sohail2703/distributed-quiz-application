package com.quiz.question.exception;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(String message) {
        super(message);
    }
}