package org.igogrzeg.recipes.recipe;

import org.igogrzeg.recipes.recipe.dtos.RecipeRequestDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeMapper {

    public RecipeRequestDto recipeEntityToRecipeRequestDto (RecipeEntity recipeEntity) {
        return RecipeRequestDto
                .builder()
                .email(recipeEntity.getUserId().getEmail().toString())
                .name(recipeEntity.getName().toString())
                .description(recipeEntity.getDescription().toString())
                .instruction(recipeEntity.getInstruction().toString())
                .preparationTime(recipeEntity.getPreparationTime().toInteger())
                .difficulty(recipeEntity.getDifficulty())
                .mealType(recipeEntity.getMealType())
                .cuisineType(recipeEntity.getCuisineType())
                .build();

    }

    public RecipeResponseDto recipeEntityToRecipeResponseDto (RecipeEntity recipentity) {
        RecipeResponseDto
                .builder()
                .author(recipentity.getUserId().getEmail())

    }

    public List<RecipeResponseDto> recipeEntityListToRecipeResponseListDto (RecipeEntity recipentity) {


    }
}
