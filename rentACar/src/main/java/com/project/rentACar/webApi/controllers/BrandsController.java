package com.project.rentACar.webApi.controllers;


import com.project.rentACar.business.abstracts.BrandService;
import com.project.rentACar.business.requests.CreateBrandRequest;
import com.project.rentACar.business.requests.UpdateBrandRequest;
import com.project.rentACar.business.responses.GetAllBrandsResponse;
import com.project.rentACar.business.responses.GetByIdBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private BrandService brandService;

    @GetMapping()
    public ResponseEntity<List<GetAllBrandsResponse>> getAll() {
        var response = brandService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}") // like /api/brands/1
    public ResponseEntity<GetByIdBrandResponse> getById(@PathVariable int id) { // get id from path
        var response = brandService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody() @Valid() CreateBrandRequest request) {
        this.brandService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping // put for update
    public ResponseEntity<Void> update(UpdateBrandRequest request) {
        this.brandService.update(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}") // like /api/brands/1
    public ResponseEntity<Void> delete(@PathVariable int id) { // get id from path
        this.brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
