package com.project.rentACar.core.utilities.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBrandNotFoundException(BrandNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("BRAND_NOT_FOUND", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ModelNameAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleModelNameAlreadyExistsException(ModelNameAlreadyExistsException ex) {
        ApiErrorResponse response = new ApiErrorResponse("MODEL_NAME_ALREADY_EXISTS", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleModelNotFoundException(ModelNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("MODEL_NOT_FOUND", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarPlateExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleCarPlateExistsException(CarPlateExistsException ex) {
        ApiErrorResponse response = new ApiErrorResponse("CAR_PLATE_EXISTS", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCarNotFoundException(CarNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("CAR_NOT_FOUND", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Generic exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleAllExceptions(Exception ex) {
        logger.error("Exception: {}", ex.getMessage(), ex);
        ProblemDetails problemDetails = new ProblemDetails("An unexpected error occurred");
        return ResponseEntity.internalServerError().body(problemDetails);
    }
}
