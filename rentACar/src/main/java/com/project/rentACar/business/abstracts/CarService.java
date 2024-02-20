package com.project.rentACar.business.abstracts;

import com.project.rentACar.business.requests.CreateCarRequest;
import com.project.rentACar.business.requests.UpdateCarRequest;
import com.project.rentACar.business.responses.GetAllCarsResponse;
import com.project.rentACar.business.responses.GetByIdCarResponse;
import com.project.rentACar.business.responses.GetByModelIdCarResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void add(CreateCarRequest request);
    void update(UpdateCarRequest request);
    void delete(int id);
    GetByIdCarResponse getById(int id);
    List<GetAllCarsResponse> getAll();
    List<GetByModelIdCarResponse> getByModelId(int modelId);
    void uploadCarImage(int carId, MultipartFile image);
}
