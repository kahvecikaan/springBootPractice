package com.project.rentACar.business.rules;

import com.project.rentACar.core.utilities.exceptions.BusinessException;
import com.project.rentACar.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private BrandRepository brandRepository;
    public void checkIfBrandNameExists(String name) {
    if (brandRepository.existsByName(name)) {
            throw new BusinessException("Brand name already exists");
        }
    }

}
