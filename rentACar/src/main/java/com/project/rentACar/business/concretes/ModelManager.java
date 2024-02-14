package com.project.rentACar.business.concretes;

import com.project.rentACar.business.abstracts.ModelService;
import com.project.rentACar.business.requests.CreateModelRequest;
import com.project.rentACar.business.responses.GetAllModelsResponse;
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

    @Override
    public List<GetAllModelsResponse> getAll() {
        var models = modelRepository.findAll();

        var response = models.stream().map(
                model -> this.modelMapperService.forResponse().map(model, GetAllModelsResponse.class)
        ).toList();

        return response;
    }

    @Override
    public void add(CreateModelRequest request) {
        var model = this.modelMapperService.forRequest().map(request, Model.class);
        this.modelRepository.save(model);
    }
}
