package com.project.rentACar.webApi.controllers;

import com.project.rentACar.business.abstracts.ModelService;
import com.project.rentACar.business.requests.CreateModelRequest;
import com.project.rentACar.business.requests.UpdateModelRequest;
import com.project.rentACar.business.responses.GetAllModelsResponse;
import com.project.rentACar.business.responses.GetByIdBrandResponse;
import com.project.rentACar.business.responses.GetByIdModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;

    @GetMapping()
    public ResponseEntity<List<GetAllModelsResponse>> getAll() {
        var response = modelService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody() @Valid() CreateModelRequest request) {
        this.modelService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping // put for update
    public ResponseEntity<Void> update(@RequestBody() @Valid() UpdateModelRequest request) {
        this.modelService.update(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        this.modelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdModelResponse> getById(@PathVariable("id") int id) {
        var response = modelService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
