package com.project.rentACar.core.utilities.exceptions;

public class InvalidBrandNameLengthException extends BusinessException{
    public InvalidBrandNameLengthException(String name, int minLength, int maxLength) {
        super("Brand name " + name + " must be between " + minLength + " and " + maxLength + " characters.");
    }
}
