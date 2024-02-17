package com.project.rentACar.core.utilities.exceptions;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(int id) {
        super("Car not found with id: " + id);
    }
}
