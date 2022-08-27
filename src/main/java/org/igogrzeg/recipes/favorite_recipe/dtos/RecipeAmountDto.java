package org.igogrzeg.recipes.favorite_recipe.dtos;

import lombok.Builder;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;

public record RecipeAmountDto(RecipeResponseDto recipeResponseDto, Long amount) {

    @Builder public RecipeAmountDto { }

}
