package com.project.rentACar.core.utilities.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BrandNameExistsException.class, InvalidBrandNameLengthException.class})
    public ResponseEntity<ApiErrorResponse> handleBusinessRuleViolationException(RuntimeException ex) {
        ApiErrorResponse response = new ApiErrorResponse("BUSINESS_RULE_VIOLATION", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        logger.error("ValidationException: {}", ex.getMessage(), ex);
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("VALIDATION_ERROR", "Validation error on input", details);
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }


    // Generic exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Exception: {}", ex.getMessage(), ex);
        ProblemDetails problemDetails = new ProblemDetails("An unexpected error occurred");
        return ResponseEntity.internalServerError().body(problemDetails);
    }
}
