package com.project.rentACar.business.concretes;

import com.project.rentACar.business.abstracts.BrandService;
import com.project.rentACar.business.requests.CreateBrandRequest;
import com.project.rentACar.business.requests.UpdateBrandRequest;
import com.project.rentACar.business.responses.GetAllBrandsResponse;
import com.project.rentACar.business.responses.GetByIdBrandResponse;
import com.project.rentACar.business.rules.BrandBusinessRules;
import com.project.rentACar.core.utilities.exceptions.BrandNotFoundException;
import com.project.rentACar.core.utilities.mappers.ModelMapperService;
import com.project.rentACar.dataAccess.abstracts.BrandRepository;
import com.project.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Business layer should be annotated with @Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandsResponse> response;
        response = brands.stream().map(
                brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)
        ).toList();

        return response;
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        var brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        return this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
    }

    @Override
    public void add(CreateBrandRequest request) {
        this.brandBusinessRules.checkIfBrandNameExists(request.getName());
        this.brandBusinessRules.validateBrandNameLength(request.getName());

        Brand brand = this.modelMapperService.forRequest().map(request, Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest request) {
        Brand brand = this.modelMapperService.forRequest().map(request, Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        this.brandRepository.deleteById(id);
    }
}
