package com.project.rentACar.business.concretes;

import com.project.rentACar.business.abstracts.CarService;
import com.project.rentACar.business.requests.CreateCarRequest;
import com.project.rentACar.business.requests.UpdateCarRequest;
import com.project.rentACar.business.responses.GetAllCarsResponse;
import com.project.rentACar.business.responses.GetByIdCarResponse;
import com.project.rentACar.business.responses.GetByModelIdCarResponse;
import com.project.rentACar.business.rules.CarBusinessRules;
import com.project.rentACar.core.utilities.exceptions.CarNotFoundException;
import com.project.rentACar.core.utilities.mappers.ModelMapperService;
import com.project.rentACar.dataAccess.abstracts.CarRepository;
import com.project.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;
    private final CarBusinessRules carBusinessRules;

    @Override
    @Transactional
    public void add(CreateCarRequest request) {
        this.carBusinessRules.checkIfPlateExists(request.getPlate());
        this.carBusinessRules.checkIfModelExists(request.getModelId());

        var car = this.modelMapperService.forRequest().map(request, Car.class);
        this.carRepository.save(car);
    }

    @Override
    @Transactional
    public void update(UpdateCarRequest request) {
        var existingCar = this.carRepository.findById(request.getId()).
                orElseThrow(() -> new CarNotFoundException(request.getId()));

        if(request.getModelId() != null) {
            this.carBusinessRules.checkIfModelExists(request.getModelId());
        }

        modelMapperService.forRequest().map(request, existingCar);
        this.carRepository.save(existingCar);
    }

    @Override
    @Transactional
    public void delete(int id) {
        var car = this.carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(car);
    }

    @Override
    public GetByIdCarResponse getById(int id) {
        var car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        return modelMapperService.forResponse().map(car, GetByIdCarResponse.class);
    }

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarsResponse> response;
        response = cars.stream().map(
                car -> this.modelMapperService.forResponse().map(car, GetAllCarsResponse.class)
        ).toList();

        return response;
    }

    @Override
    public List<GetByModelIdCarResponse> getByModelId(int modelId) {
        this.carBusinessRules.checkIfModelExists(modelId);

        List<Car> cars = carRepository.findByModelId(modelId);
        List<GetByModelIdCarResponse> response;
        response = cars.stream().map(
                car -> this.modelMapperService.forResponse().map(car, GetByModelIdCarResponse.class)
        ).toList();

        return response;
    }
}
