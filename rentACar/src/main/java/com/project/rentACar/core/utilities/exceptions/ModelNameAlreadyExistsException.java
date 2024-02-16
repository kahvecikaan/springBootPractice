package com.project.rentACar.core.utilities.exceptions;

public class ModelNameAlreadyExistsException extends RuntimeException {
    public ModelNameAlreadyExistsException(String name) {
        super("Model name " + name + " already exists");
    }
}
