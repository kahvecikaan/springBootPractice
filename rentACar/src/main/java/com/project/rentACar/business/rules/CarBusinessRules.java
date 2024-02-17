package com.project.rentACar.business.rules;

import com.project.rentACar.core.utilities.exceptions.CarPlateExistsException;
import com.project.rentACar.core.utilities.exceptions.ModelNotFoundException;
import com.project.rentACar.dataAccess.abstracts.CarRepository;
import com.project.rentACar.dataAccess.abstracts.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository carRepository;
    private final ModelRepository modelRepository;

    public void checkIfPlateExists(String plate) {
        if (carRepository.existsByPlate(plate)) {
            throw new CarPlateExistsException(plate);
        }
    }

    public void checkIfModelExists(int modelId) {
        if (!modelRepository.existsById(modelId)) {
            throw new ModelNotFoundException(modelId);
        }
    }
}
