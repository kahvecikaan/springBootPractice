package com.project.rentACar.core.validation.annotations;

import com.project.rentACar.core.validation.validators.ModelYearValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ModelYearValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidModelYear {
    String message() default "Invalid model year";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
