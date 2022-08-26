package org.igogrzeg.recipes.ingredient.dtos;

import lombok.Builder;
import org.igogrzeg.recipes.ingredient.enums.Unit;

public record IngredientResponseDto(
        String name,
        Integer quantity,
        Unit unit
        ) {

    @Builder
    public IngredientResponseDto { }

}
