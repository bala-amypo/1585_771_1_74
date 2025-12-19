package com.example.demo.exception;

import com.example.demo.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponse handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ApiErrorResponse handleBadCredentials(
            BadCredentialsException ex,
            HttpServletRequest request) {

        return new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                message,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiErrorResponse handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        return new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "CONSTRAINT_VIOLATION",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        return new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                ex.getMessage(),
                request.getRequestURI()
        );
    }
}
