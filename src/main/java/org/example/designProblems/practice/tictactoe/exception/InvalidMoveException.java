package org.example.designProblems.practice.tictactoe.exception;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(String message) {
        super(message);
    }
}
