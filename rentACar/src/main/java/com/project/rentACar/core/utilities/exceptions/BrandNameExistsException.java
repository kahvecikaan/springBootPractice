package com.project.rentACar.core.utilities.exceptions;

public class BrandNameExistsException extends BusinessException {
    public BrandNameExistsException(String name) {
        super("Brand name " + name + " already exists");
    }
}
