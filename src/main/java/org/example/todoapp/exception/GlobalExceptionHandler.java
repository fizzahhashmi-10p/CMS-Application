package org.example.todoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Object> handleResourceNotFoundException(
                ResourceNotFoundException ex, WebRequest request) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(InvalidCredentialsException.class)
        public ResponseEntity<Object> handleInvalidCredentialsException(
                InvalidCredentialsException ex, WebRequest request) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



