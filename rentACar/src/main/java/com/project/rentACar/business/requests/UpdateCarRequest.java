package com.project.rentACar.business.requests;

import com.project.rentACar.core.validation.annotations.ValidModelYear;
import com.project.rentACar.core.validation.annotations.ValidState;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

    @NotNull
    private int id; // Keep as primitive since ID is required

    @Min(value = 0, message = "Daily price cannot be less than 0.")
    private Double dailyPrice; // Use Double instead of double to allow null values

    @ValidModelYear
    private Integer modelYear;

    @ValidState
    private Integer state;

    private Integer modelId; // Use Integer to allow null values

    @Pattern(regexp = "^(0[1-9]|[1-7][0-9]|8[0-1]) ?([A-Za-z]{1,3}) ?([0-9]{1,4})$", message = "Invalid plate format.")
    private String plate;
}
