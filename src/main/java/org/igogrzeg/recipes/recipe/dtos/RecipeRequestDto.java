package org.igogrzeg.recipes.recipe.dtos;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.recipe.enums.CuisineType;
import org.igogrzeg.recipes.recipe.enums.Difficulty;
import org.igogrzeg.recipes.recipe.enums.MealType;

import java.util.List;

public record RecipeRequestDto(Long userId, String email, String name, String description,
                               String instruction, Integer preparationTime,
                               Difficulty difficulty, MealType mealType,
                               CuisineType cuisineType) {

    @Builder
    public RecipeRequestDto {}
}
