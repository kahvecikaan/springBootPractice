package com.project.rentACar.webApi.controllers;

import com.project.rentACar.business.abstracts.ModelService;
import com.project.rentACar.business.requests.CreateModelRequest;
import com.project.rentACar.business.responses.GetAllModelsResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;

    @GetMapping()
    public List<GetAllModelsResponse> getAll() {
        return modelService.getAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED) // 201
    public void add(@RequestBody() @Valid() CreateModelRequest request) {
        this.modelService.add(request);
    }
}
