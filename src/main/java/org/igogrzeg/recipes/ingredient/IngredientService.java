package org.igogrzeg.recipes.ingredient;

import org.igogrzeg.recipes.basic.ErrorsListDto;
import org.igogrzeg.recipes.ingredient.dtos.IngredientRequestDto;
import org.igogrzeg.recipes.ingredient.dtos.IngredientResponseDto;
import org.igogrzeg.recipes.ingredient.exceptions.IngredientNotFoundException;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientService {

    private static final String POLISH_ALPHABET = "[a-zA-Z-\\p{IsAlphabetic}]+";
    private final IngredientMapper ingredientMapper;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService (IngredientMapper ingredientMapper,
                              IngredientRepository ingredientRepository) {
        this.ingredientMapper = ingredientMapper;
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientResponseDto getIngredientById(Long id){
        return ingredientMapper.ingredientEntityToIngredientResponseDto(ingredientRepository.findIngredientById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id)));
    }

    public List<IngredientResponseDto> getAllIngredients() {
        List<IngredientEntity> ingredients = ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return ingredientMapper.ingredientEntityListToIngredientResponseDtoList((ingredients));
    }

    public ErrorsListDto addIngredient(IngredientRequestDto ingredientRequestDto){

        ErrorsListDto errors = new ErrorsListDto(new HashMap<String, String>());
        if(Objects.isNull(ingredientRequestDto.name()))
            errors.add("Name cannot be null...", "name");
        if(!ingredientRequestDto.name().containsValidCharacters(ingredientRequestDto.name().toString(), POLISH_ALPHABET))
            errors.add("Name must contain only letters from Polish Alphabet...", "name");
        if(Objects.isNull(ingredientRequestDto.quantity()))
            errors.add("Quantity cannot be null...", "quantity");
        if(errors.isListOfErrorsEmpty()) {
            IngredientEntity ingredientEntity = ingredientMapper.ingredientRequestDtoToIngredientEntity(ingredientRequestDto);
            ingredientRepository.save(ingredientEntity);
        }
        return errors;
    }

    public String deleteIngredientById(Long id){
        ingredientRepository
                .delete(ingredientRepository.findIngredientById(id)
                        .orElseThrow(() -> new IngredientNotFoundException(id)));
        return "Ingredient number " + id + " has been successfully deleted";
    }


}
