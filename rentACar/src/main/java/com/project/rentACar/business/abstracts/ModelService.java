package com.project.rentACar.business.abstracts;

import com.project.rentACar.business.requests.CreateModelRequest;
import com.project.rentACar.business.requests.UpdateModelRequest;
import com.project.rentACar.business.responses.GetAllModelsResponse;
import com.project.rentACar.business.responses.GetByIdBrandResponse;
import com.project.rentACar.business.responses.GetByIdModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    void add(CreateModelRequest request);
    void update(UpdateModelRequest request);
    void delete(int id);
    GetByIdModelResponse getById(int id);
}
