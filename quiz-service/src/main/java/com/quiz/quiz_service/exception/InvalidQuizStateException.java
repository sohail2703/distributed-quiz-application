package com.quiz.quiz_service.exception;

public class InvalidQuizStateException extends RuntimeException {

    public InvalidQuizStateException(String message) {
        super(message);
    }
}