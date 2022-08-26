package org.igogrzeg.recipes.recipe;

import org.igogrzeg.recipes.recipe.dtos.RecipeRequestDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.igogrzeg.recipes.recipe.exceptions.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Autowired
    public RecipeService (RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    public List<RecipeResponseDto> getAllRecipes() {
        return recipeMapper.recipeEntityListToRecipeResponseListDto(
                recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    public RecipeResponseDto getRecipeResponseDtoById(Long id) {
        return recipeMapper.recipeEntityToRecipeResponseDto(getRecipeEntityById(id));
    }

    public RecipeEntity getRecipeEntityById(Long id) {
        return recipeRepository.findRecipeById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }

    public RecipeRequestDto addRecipe(RecipeEntity recipeEntity) {
        return recipeMapper.recipeEntityToRecipeRequestDto(recipeRepository.save(recipeEntity));
    }

    public List<RecipeResponseDto> getRecipeResponseDtosByIds(List<Long> recipeEntitiesIds) {
        var recipes = recipeRepository.findAllById(recipeEntitiesIds);
        return recipeMapper.recipeEntityListToRecipeResponseListDto(recipes);
    }


}
