package org.igogrzeg.recipes.recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.interfaces.Validator;
import org.igogrzeg.recipes.recipe.dtos.RecipeRequestDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.igogrzeg.recipes.recipe.exceptions.RecipeNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RecipeService implements Validator {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;


    public RecipeEntity getRecipeEntityById(Long id) {

        return recipeRepository.findRecipeById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }

    public List<RecipeResponseDto> getAllRecipes() {

        return recipeMapper.recipeEntityListToRecipeResponseListDto(
                recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    public RecipeResponseDto getRecipeResponseDtoById(Long id) {

        return recipeMapper.recipeEntityToRecipeResponseDto(getRecipeEntityById(id));
    }

    public List<RecipeResponseDto> getRecipeResponseDtosByIds(List<Long> recipeEntitiesIds) {

        var recipes = recipeRepository.findAllById(recipeEntitiesIds);
        return recipeMapper.recipeEntityListToRecipeResponseListDto(recipes);
    }

    public ErrorsMapDto addRecipe(RecipeRequestDto recipeRequestDto) {

        ErrorsMapDto errors = new ErrorsMapDto(new HashMap<String, String>());

        if( Objects.isNull(recipeRequestDto.email()) )
            errors.add("Email cannot be null...", "email");

        if( Objects.isNull(recipeRequestDto.name()) )
            errors.add("Email cannot be null...", "email");

        if( !containsPolishCharacters(recipeRequestDto.name()) )
            errors.add("Name must contain only Polish characters... ", "email");

        if( !containsValidCharacters(recipeRequestDto.description(), ENGLISH_LETTERS_NUMBERS_SPECIAL_CHARACTERS) )
            errors.add("Description must contain only letters, numbers and special characters... ", "description");

        if( !containsValidCharacters(recipeRequestDto.instruction(), ENGLISH_LETTERS_NUMBERS_SPECIAL_CHARACTERS) )
            errors.add("Instruction must contain only letters, numbers and special characters... ", "instruction");

        if( !isValidNumber(recipeRequestDto.preparationTime(), 5, 300) )
            errors.add("Preparation time must be between 5 and 300 minutes...", "preparationTime");

        if(errors.isListOfErrorsEmpty())
            recipeRepository.save(recipeMapper.recipeRequestDtoToRecipeEntity(recipeRequestDto));

        return errors;
    }

    //TO CHANGE
    public ErrorsMapDto changeRecipeById(Long id, RecipeRequestDto recipeRequestDto) {

        ErrorsMapDto errors = new ErrorsMapDto(new HashMap<String, String>());
        try {
            RecipeEntity recipeEntity = getRecipeEntityById(id);
            recipeEntity.changeName(recipeRequestDto.name());
            recipeEntity.changeDescription(recipeRequestDto.description());
            recipeEntity.changeInstruction(recipeRequestDto.instruction());
            recipeEntity.changePreparationTime(recipeRequestDto.preparationTime());
            recipeEntity.changeDifficulty(recipeRequestDto.difficulty());
            recipeEntity.changeMealType(recipeRequestDto.mealType());
            recipeEntity.changeCuisineType(recipeRequestDto.cuisineType());
        }
        catch (RecipeNotFoundException exception) {
            errors.add(new RecipeNotFoundException(id).getMessage(), "id");
        }
        return errors;
    }
}
