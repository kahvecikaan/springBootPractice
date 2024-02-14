package com.project.rentACar;

import com.project.rentACar.core.utilities.exceptions.BusinessException;
import com.project.rentACar.core.utilities.exceptions.ProblemDetails;
import com.project.rentACar.core.utilities.exceptions.ValidationProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@SpringBootApplication
@RestControllerAdvice
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}

	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException exception) {
		var problemDetails = new ProblemDetails();
		problemDetails.setMessage(exception.getMessage());
		return problemDetails;
	}

	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
		var problemDetails = new ValidationProblemDetails();
		problemDetails.setMessage("Validation error on input");
		problemDetails.setValidationErrors(new HashMap<>());

		for (var fieldError : exception.getBindingResult().getFieldErrors()) {
			problemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return problemDetails;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
