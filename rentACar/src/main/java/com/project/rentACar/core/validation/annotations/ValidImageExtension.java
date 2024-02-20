package com.project.rentACar.core.validation.annotations;

import jakarta.validation.Payload;
import com.project.rentACar.core.validation.validators.ImageExtensionValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.METHOD;

@Documented
@Constraint(validatedBy = ImageExtensionValidator.class)
@Target({METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface ValidImageExtension {
    String message() default "Invalid image extension";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] allowedExtensions() default {"jpg", "jpeg", "png"};
}
