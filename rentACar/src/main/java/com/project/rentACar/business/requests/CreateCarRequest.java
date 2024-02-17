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
public class CreateCarRequest {
    @NotNull(message = "Daily price cannot be null.")
    @Min(value = 0, message = "Daily price cannot be less than 0.")
    private double dailyPrice;

    @NotNull(message = "Model year cannot be null.")
    @ValidModelYear
    private int modelYear;

    @NotNull(message = "State cannot be null.")
    @ValidState
    private int state;

    @NotNull(message = "Model id cannot be null.")
    private int modelId;

    @Pattern(regexp = "^(0[1-9]|[1-7][0-9]|8[0-1]) ?([A-Za-z]{1,3}) ?([0-9]{1,4})$", message = "Invalid plate format.")
    private String plate;
}
