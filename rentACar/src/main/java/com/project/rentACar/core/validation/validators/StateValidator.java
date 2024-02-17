package com.project.rentACar.core.validation.validators;

import com.project.rentACar.core.validation.annotations.ValidState;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidator implements ConstraintValidator<ValidState, Integer> {
    @Override
    public void initialize(ValidState constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer state, ConstraintValidatorContext context) {
        // Consider null as valid to support optional update fields
        if (state == null) {
            return true;
        }

        return state >= 0 && state <= 2;
    }
}

