package com.project.rentACar.core.utilities.exceptions;

public class CarPlateExistsException extends RuntimeException{
    public CarPlateExistsException(String plate) {
        super("Car plate already exists: " + plate);
    }
}
