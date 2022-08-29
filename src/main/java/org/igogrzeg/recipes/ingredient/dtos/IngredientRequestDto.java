package org.igogrzeg.recipes.ingredient.dtos;

import lombok.Builder;
import org.igogrzeg.recipes.ingredient.enums.Unit;
import org.igogrzeg.recipes.ingredient.valueObjects.QuantityValidator;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;

public record IngredientRequestDto(

        Long recipeId,

        String name,

        Integer quantity,

        Unit unit
        ) {

        @Builder
        public IngredientRequestDto {}


}
