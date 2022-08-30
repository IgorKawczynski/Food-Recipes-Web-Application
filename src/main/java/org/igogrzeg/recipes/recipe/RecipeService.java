package org.igogrzeg.recipes.recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.basic.exceptions.NullValueException;
import org.igogrzeg.recipes.basic.exceptions.InValidCharactersException;
import org.igogrzeg.recipes.basic.exceptions.PolishSignsException;
import org.igogrzeg.recipes.interfaces.Validator;
import org.igogrzeg.recipes.recipe.dtos.RecipeRequestDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.igogrzeg.recipes.recipe.exceptions.PreparationTimeException;
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

        ErrorsMapDto errors = new ErrorsMapDto(new HashMap<>());

        if( Objects.isNull(recipeRequestDto.email()) )
            errors.add("email", new NullValueException("Email"));

        if( Objects.isNull(recipeRequestDto.recipeName()) )
            errors.add("recipeName", new NullValueException("Recipe name"));

        if( !containsPolishCharacters(recipeRequestDto.recipeName()) )
            errors.add("recipeName", new PolishSignsException("Recipe name"));

        if( !containsValidCharacters(recipeRequestDto.description(), ENGLISH_LETTERS_NUMBERS_SPECIAL_CHARACTERS) )
            errors.add("description", new InValidCharactersException("Description"));

        if( !containsValidCharacters(recipeRequestDto.instruction(), ENGLISH_LETTERS_NUMBERS_SPECIAL_CHARACTERS) )
            errors.add("instruction", new InValidCharactersException("Instruction"));

        if( !isValidNumber(recipeRequestDto.preparationTime(), 5, 300) )
            errors.add("preparationTime", new PreparationTimeException());

        if( errors.isEmpty() )
            recipeRepository.save(recipeMapper.recipeRequestDtoToRecipeEntity(recipeRequestDto));

        return errors;
    }

    //TO CHANGE
    public ErrorsMapDto changeRecipeById(Long id, RecipeRequestDto recipeRequestDto) {

        ErrorsMapDto errors = new ErrorsMapDto(new HashMap<>());
        try {
            RecipeEntity recipeEntity = getRecipeEntityById(id);
            recipeEntity.changeName(recipeRequestDto.recipeName());
            recipeEntity.changeDescription(recipeRequestDto.description());
            recipeEntity.changeInstruction(recipeRequestDto.instruction());
            recipeEntity.changePreparationTime(recipeRequestDto.preparationTime());
            recipeEntity.changeDifficulty(recipeRequestDto.difficulty());
            recipeEntity.changeMealType(recipeRequestDto.mealType());
            recipeEntity.changeCuisineType(recipeRequestDto.cuisineType());
        }
        catch (RecipeNotFoundException exception) {
            errors.add("", new RecipeNotFoundException(id));
        }
        return errors;
    }
}
