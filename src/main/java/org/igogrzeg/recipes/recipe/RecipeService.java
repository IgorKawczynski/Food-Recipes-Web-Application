package org.igogrzeg.recipes.recipe;

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

    public RecipeResponseDto getRecipeById(Long id) {
        return recipeMapper.recipeEntityToRecipeResponseDto(recipeRepository.findRecipeById(id).orElseThrow(() -> new RecipeNotFoundException(id)));
    }

}
