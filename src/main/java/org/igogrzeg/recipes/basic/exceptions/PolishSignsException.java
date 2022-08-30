package org.igogrzeg.recipes.basic.exceptions;

public class PolishSignsException extends RuntimeException {

    private final static String MESSAGE = " must contain only Polish characters... ";

    public PolishSignsException(String fieldName) {
        super(fieldName + MESSAGE);
    }

}
