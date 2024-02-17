package com.project.rentACar.core.validation.annotations;

import com.project.rentACar.core.validation.validators.StateValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = StateValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidState {
    String message() default "Invalid state";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
