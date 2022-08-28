package org.igogrzeg.recipes.favorite_recipe.dtos;

import lombok.Builder;
import org.igogrzeg.recipes.recipe.enums.MealType;

public record FavoriteRecipeAmountDto(String author, String recipeName, MealType mealType, Long numberOfAdditionsToFavorites) {

    @Builder public FavoriteRecipeAmountDto { }

}
