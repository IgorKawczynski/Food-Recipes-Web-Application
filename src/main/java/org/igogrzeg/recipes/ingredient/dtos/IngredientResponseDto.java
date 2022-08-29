package org.igogrzeg.recipes.ingredient.dtos;

import lombok.Builder;
import org.igogrzeg.recipes.ingredient.enums.Unit;
import org.igogrzeg.recipes.ingredient.valueObjects.QuantityValidator;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;

public record IngredientResponseDto(

        String name,
        Integer quantity,
        Unit unit
        ) {

    @Builder
    public IngredientResponseDto { }

}
