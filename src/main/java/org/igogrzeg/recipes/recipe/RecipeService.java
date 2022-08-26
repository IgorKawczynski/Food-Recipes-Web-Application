package org.igogrzeg.recipes.recipe;

import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.ingredient.IngredientEntity;
import org.igogrzeg.recipes.recipe.dtos.RecipeRequestDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.igogrzeg.recipes.recipe.exceptions.RecipeNotFoundException;
import org.igogrzeg.recipes.user.UserEntity;
import org.igogrzeg.recipes.user.UserRepository;
import org.igogrzeg.recipes.user.UserService;
import org.igogrzeg.recipes.user.exceptions.NoSuchUserExists;
import org.igogrzeg.recipes.user.valueObjects.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final UserRepository userRepository;


    @Autowired
    public RecipeService (RecipeRepository recipeRepository,
                          RecipeMapper recipeMapper,
                          UserRepository userRepository) {

        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.userRepository = userRepository;
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

    public ErrorsMapDto addRecipe(RecipeRequestDto recipeRequestDto) {

        ErrorsMapDto errors = new ErrorsMapDto(new HashMap<String, String>());
        recipeRepository.save(recipeMapper.recipeRequestDtoToRecipeEntity(recipeRequestDto));
        return errors;
    }

    public List<RecipeResponseDto> getRecipeResponseDtosByIds(List<Long> recipeEntitiesIds) {
        var recipes = recipeRepository.findAllById(recipeEntitiesIds);
        return recipeMapper.recipeEntityListToRecipeResponseListDto(recipes);
    }


}
