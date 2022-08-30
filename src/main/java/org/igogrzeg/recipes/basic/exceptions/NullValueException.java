package org.igogrzeg.recipes.basic.exceptions;

public class NullValueException extends RuntimeException{

    private static final String MESSAGE = " can't be null!";

    public NullValueException(String fieldName) {
        super(fieldName + MESSAGE);
    }

}
