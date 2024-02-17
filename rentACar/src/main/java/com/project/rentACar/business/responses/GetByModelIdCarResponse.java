package com.project.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByModelIdCarResponse {
    private int id;
    private int brandId;
    private String brandName;
    private int modelId;
    private String modelName;
    private int state;
}
