package org.igogrzeg.recipes.ingredient.dtos;

import lombok.Builder;
import org.igogrzeg.recipes.ingredient.enums.Unit;

public record IngredientRequestDto(Long recipeId, String name, Integer quantity, Unit unit) {

        @Builder
        public IngredientRequestDto {}

}
