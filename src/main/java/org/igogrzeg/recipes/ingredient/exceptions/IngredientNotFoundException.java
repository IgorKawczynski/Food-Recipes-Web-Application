package org.igogrzeg.recipes.ingredient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException(Long id) {
        super("Ingredient number " + id + " does not exist...");
    }

}
