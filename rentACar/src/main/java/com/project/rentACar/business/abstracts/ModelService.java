package com.project.rentACar.business.abstracts;

import com.project.rentACar.business.requests.CreateModelRequest;
import com.project.rentACar.business.responses.GetAllModelsResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    void add(CreateModelRequest request);
}
