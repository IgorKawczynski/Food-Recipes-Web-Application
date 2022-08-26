package org.igogrzeg.recipes.favorite_recipe.dtos;

import lombok.Builder;

public record FavoriteRecipeDto(Long userId, Long recipeId ) {

    @Builder public FavoriteRecipeDto { }

}
