package org.igogrzeg.recipes.ingredient.dtos;

import org.igogrzeg.recipes.ingredient.enums.Unit;
import org.igogrzeg.recipes.ingredient.valueObjects.QuantityValidator;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;

public record IngredientRequestDto(
        Long recipeId,
        NameValidator name,
        QuantityValidator quantity,
        Unit unit
        ) {



}
