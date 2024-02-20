package com.project.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarsResponse {
    private int id;
    private double dailyPrice;
    private int modelYear;
    private String plate;
    private int state;
    private int modelId;
    private String imagePath;
}
