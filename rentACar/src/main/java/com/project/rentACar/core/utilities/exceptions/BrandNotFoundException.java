package com.project.rentACar.core.utilities.exceptions;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(int id ) {
        super("Brand name " + id + " not found");
    }
}
