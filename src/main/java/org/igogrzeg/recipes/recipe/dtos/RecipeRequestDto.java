package org.igogrzeg.recipes.recipe.dtos;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.ingredient.IngredientEntity;
import org.igogrzeg.recipes.recipe.enums.CuisineType;
import org.igogrzeg.recipes.recipe.enums.Difficulty;
import org.igogrzeg.recipes.recipe.enums.MealType;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;

import java.util.List;

public record RecipeRequestDto(String email, List<IngredientEntity> ingredients,
                               String name, String description,
                               String instruction, Integer preparationTime,
                               Difficulty difficulty, MealType mealType,
                               CuisineType cuisineType) {

    @Builder
    public RecipeRequestDto {}
}
