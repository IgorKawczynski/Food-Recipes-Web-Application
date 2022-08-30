package org.igogrzeg.recipes.recipe.exceptions;

public class PreparationTimeException extends RuntimeException {

    private final static String MESSAGE = "Preparation time must be between 5 and 300 minutes...";

    public PreparationTimeException() {
        super(MESSAGE);
    }

}
