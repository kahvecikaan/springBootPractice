package com.project.rentACar.webApi.controllers;

import com.project.rentACar.business.abstracts.CarService;
import com.project.rentACar.business.requests.CreateCarRequest;
import com.project.rentACar.business.requests.UpdateCarRequest;
import com.project.rentACar.business.responses.GetAllCarsResponse;
import com.project.rentACar.business.responses.GetByIdCarResponse;
import com.project.rentACar.business.responses.GetByModelIdCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private final CarService carService;

    @GetMapping()
    public ResponseEntity<List<GetAllCarsResponse>> getAll() {
        var response = carService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}") // like /api/cars/1
    public ResponseEntity<GetByIdCarResponse> getById(@PathVariable int id) { // get id from path
        var response = carService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/model/{modelId}") // like /api/cars/model/1
    public ResponseEntity<List<GetByModelIdCarResponse>> getByModelId(@PathVariable int modelId) { // get id from path
        var response = carService.getByModelId(modelId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody() @Valid() CreateCarRequest request) {
        this.carService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping // put for update
    public ResponseEntity<Void> update(@RequestBody() @Valid() UpdateCarRequest request) {
        this.carService.update(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}") // like /api/cars/1
    public ResponseEntity<Void> delete(@PathVariable int id) { // get id from path
        this.carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
