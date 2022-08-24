package org.igogrzeg.recipes.recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.recipe.dtos.RecipeRequestDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.igogrzeg.recipes.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    private final UserMapper userMapper;
    @Autowired
    public RecipeMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

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

    public RecipeResponseDto recipeEntityToRecipeResponseDto (RecipeEntity recipeEntity) {
        String author = userMapper.setNickname(recipeEntity.getUserId().getEmail());
        return RecipeResponseDto
                .builder()
                .author(author)
                .name(recipeEntity.getName().toString())
                .description(recipeEntity.getDescription().toString())
                .instruction(recipeEntity.getInstruction().toString())
                .preparationTime(recipeEntity.getPreparationTime().toInteger())
                .difficulty(recipeEntity.getDifficulty())
                .mealType(recipeEntity.getMealType())
                .cuisineType(recipeEntity.getCuisineType())
                .build();


    }

    public List<RecipeResponseDto> recipeEntityListToRecipeResponseListDto (List<RecipeEntity> recipeEntityList) {
        return recipeEntityList.stream().map(this::recipeEntityToRecipeResponseDto).collect(Collectors.toList());
    }
}
