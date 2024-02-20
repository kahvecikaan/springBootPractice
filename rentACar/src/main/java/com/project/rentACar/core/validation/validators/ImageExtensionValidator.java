package com.project.rentACar.core.validation.validators;

import com.project.rentACar.core.validation.annotations.ValidImageExtension;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class ImageExtensionValidator implements ConstraintValidator<ValidImageExtension, MultipartFile> {
    private List<String> allowedExtensions;

    @Override
    public void initialize(ValidImageExtension constraintAnnotation) {
        allowedExtensions = Arrays.asList(constraintAnnotation.allowedExtensions());
    }
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return allowedExtensions.contains(fileExtension);
    }
}
