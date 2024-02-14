package com.project.rentACar.webApi.controllers;


import com.project.rentACar.business.abstracts.BrandService;
import com.project.rentACar.business.requests.CreateBrandRequest;
import com.project.rentACar.business.requests.UpdateBrandRequest;
import com.project.rentACar.business.responses.GetAllBrandsResponse;
import com.project.rentACar.business.responses.GetByIdBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private BrandService brandService;

    @GetMapping()
    public List<GetAllBrandsResponse> getAll() {
        return this.brandService.getAll();
    }

    @GetMapping("/{id}") // like /api/brands/1
    public GetByIdBrandResponse getById(@PathVariable int id) { // get id from path
        return brandService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED) // 201
    public void add(@RequestBody() @Valid() CreateBrandRequest request) {
        this.brandService.add(request);
    }

    @PutMapping // put for update
    public void update(UpdateBrandRequest request) {
        this.brandService.update(request);
    }

    @DeleteMapping("/{id}") // like /api/brands/1
    public void delete(@PathVariable int id) { // get id from path
        this.brandService.delete(id);
    }
}
