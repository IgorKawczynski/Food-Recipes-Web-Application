package org.igogrzeg.recipes.favorite_recipe;

import org.igogrzeg.recipes.favorite_recipe.dtos.FavoriteRecipeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoriteRecipeMapper {

    public FavoriteRecipeDto favoriteRecipeEntityToFavoriteRecipeRequestDto(FavoriteRecipeEntity favoriteRecipe) {
        return FavoriteRecipeDto.builder()
                .userId(favoriteRecipe.getUserEntity().getId())
                .recipeId(favoriteRecipe.getRecipeEntity().getId())
                .build();
    }

    public List<FavoriteRecipeDto> favoriteRecipeEntitiesToFavoriteRecipeRequestDtoList(List<FavoriteRecipeEntity> favoriteRecipes) {
        return favoriteRecipes.stream()
                .map(this::favoriteRecipeEntityToFavoriteRecipeRequestDto)
                .collect(Collectors.toList());
    }

}
