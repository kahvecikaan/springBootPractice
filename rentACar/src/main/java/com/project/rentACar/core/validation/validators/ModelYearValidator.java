package com.project.rentACar.core.validation.validators;

import com.project.rentACar.core.validation.annotations.ValidModelYear;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class ModelYearValidator implements ConstraintValidator<ValidModelYear, Integer> {
    @Override
    public void initialize(ValidModelYear constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer modelYear, ConstraintValidatorContext context) {
        // Consider null as valid to support optional update fields
        if (modelYear == null) {
            return true;
        }

        int currentYear = Year.now().getValue();
        return modelYear >= 1990 && modelYear <= currentYear + 1;
    }

}
