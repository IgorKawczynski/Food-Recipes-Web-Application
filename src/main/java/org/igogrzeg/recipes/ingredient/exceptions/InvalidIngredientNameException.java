package org.igogrzeg.recipes.ingredient.exceptions;

public class InvalidIngredientNameException extends RuntimeException {

    private static final String MESSAGE = "Name must contain only letters from Polish Alphabet...";

    public InvalidIngredientNameException() {
        super(MESSAGE);
    }

}
