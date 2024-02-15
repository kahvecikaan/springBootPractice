package com.project.rentACar.business.rules;

import com.project.rentACar.core.utilities.exceptions.BrandNameExistsException;
import com.project.rentACar.core.utilities.exceptions.InvalidBrandNameLengthException;
import com.project.rentACar.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private BrandRepository brandRepository;
    public void checkIfBrandNameExists(String name) throws BrandNameExistsException{
    if (brandRepository.existsByName(name)) {
            throw new BrandNameExistsException(name);
        }
    }

    public void validateBrandNameLength(String name) {
        int minLength = 3;
        int maxLength = 50;

        if(name.length() < minLength || name.length() > maxLength) {
            throw new InvalidBrandNameLengthException(name, minLength, maxLength);
        }
    }
}
