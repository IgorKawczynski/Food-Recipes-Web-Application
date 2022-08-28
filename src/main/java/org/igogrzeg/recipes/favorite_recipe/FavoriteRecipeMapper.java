package org.igogrzeg.recipes.favorite_recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.favorite_recipe.dtos.FavoriteRecipeAmountDto;
import org.igogrzeg.recipes.favorite_recipe.dtos.FavoriteRecipeDto;
import org.igogrzeg.recipes.favorite_recipe.dtos.FavoriteRecipeAmountHelper;
import org.igogrzeg.recipes.recipe.RecipeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FavoriteRecipeMapper {

    private final RecipeMapper recipeMapper;

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

    public FavoriteRecipeAmountDto favoriteRecipeAmountHelperToFavoriteRecipeAmountDto(FavoriteRecipeAmountHelper frah) {
        var recipeResponseDto = recipeMapper.recipeEntityToRecipeResponseDto(frah.recipe());
        return FavoriteRecipeAmountDto.builder()
                .author(recipeResponseDto.author())
                .recipeName(recipeResponseDto.name())
                .mealType(recipeResponseDto.mealType())
                .numberOfAdditionsToFavorites(frah.amount())
                .build();
    }

    public List<FavoriteRecipeAmountDto> favoriteRecipeAmountHelperListToFavoriteRecipeAmountDtoList(List<FavoriteRecipeAmountHelper> frahs) {
        return frahs.stream()
                .map(this::favoriteRecipeAmountHelperToFavoriteRecipeAmountDto)
                .collect(Collectors.toList());
    }

}
