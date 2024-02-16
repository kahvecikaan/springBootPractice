package com.project.rentACar.core.utilities.exceptions;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(int id) {
        super("Model not found with id: " + id);
    }
}
