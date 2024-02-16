package com.project.rentACar.business.concretes;

import com.project.rentACar.business.abstracts.ModelService;
import com.project.rentACar.business.requests.CreateModelRequest;
import com.project.rentACar.business.requests.UpdateModelRequest;
import com.project.rentACar.business.responses.GetAllModelsResponse;
import com.project.rentACar.business.responses.GetByIdBrandResponse;
import com.project.rentACar.business.responses.GetByIdModelResponse;
import com.project.rentACar.business.rules.ModelBusinessRules;
import com.project.rentACar.core.utilities.exceptions.ModelNotFoundException;
import com.project.rentACar.core.utilities.mappers.ModelMapperService;
import com.project.rentACar.dataAccess.abstracts.ModelRepository;
import com.project.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;

    @Override
    public List<GetAllModelsResponse> getAll() {
        var models = modelRepository.findAll();

        List<GetAllModelsResponse> response;
        response = models.stream().map(
                model -> this.modelMapperService.forResponse().map(model, GetAllModelsResponse.class)
        ).toList();

        return response;
    }

    @Override
    public void add(CreateModelRequest request) {
        this.modelBusinessRules.checkIfBrandExists(request.getBrandId());
        this.modelBusinessRules.checkIfModelNameExistWithinBrand(request.getName(), request.getBrandId());

        var model = this.modelMapperService.forRequest().map(request, Model.class);
        this.modelRepository.save(model);
    }

    @Override
    public void update(UpdateModelRequest request) {
        var model = this.modelMapperService.forRequest().map(request, Model.class);
        this.modelRepository.save(model);
    }

    @Override
    public void delete(int id) {
        this.modelRepository.deleteById(id);
    }

    @Override
    public GetByIdModelResponse getById(int id) {

        var model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        return this.modelMapperService.forResponse().map(model, GetByIdModelResponse.class);
    }
}
