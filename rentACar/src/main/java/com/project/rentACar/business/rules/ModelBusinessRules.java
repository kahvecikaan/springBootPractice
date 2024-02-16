package com.project.rentACar.business.rules;

import com.project.rentACar.core.utilities.exceptions.BrandNotFoundException;
import com.project.rentACar.core.utilities.exceptions.ModelNameAlreadyExistsException;
import com.project.rentACar.dataAccess.abstracts.BrandRepository;
import com.project.rentACar.dataAccess.abstracts.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public void checkIfModelNameExistWithinBrand(String name, int brandId) {
        boolean exists = modelRepository.existsByNameAndBrandId(name, brandId);
        if (exists) {
            throw new ModelNameAlreadyExistsException(name);
        }
    }

    public void checkIfBrandExists(int brandId) {
        boolean exists = brandRepository.existsById(brandId);
        if (!exists) {
            throw new BrandNotFoundException(brandId);
        }
    }
}
