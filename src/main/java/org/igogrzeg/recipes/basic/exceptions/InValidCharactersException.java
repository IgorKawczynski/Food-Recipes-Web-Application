package org.igogrzeg.recipes.basic.exceptions;

public class InValidCharactersException extends RuntimeException {

    public InValidCharactersException(String fieldName) {
        super(fieldName + " must contain only letters, numbers and special characters...");
    }

}
