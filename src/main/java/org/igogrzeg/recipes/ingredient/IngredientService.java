package org.igogrzeg.recipes.ingredient;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.basic.exceptions.NullValueException;
import org.igogrzeg.recipes.ingredient.dtos.IngredientRequestDto;
import org.igogrzeg.recipes.ingredient.dtos.IngredientResponseDto;
import org.igogrzeg.recipes.ingredient.exceptions.IngredientNotFoundException;
import org.igogrzeg.recipes.ingredient.exceptions.InvalidIngredientNameException;
import org.igogrzeg.recipes.interfaces.Validator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IngredientService implements Validator {

    private final IngredientMapper ingredientMapper;
    private final IngredientRepository ingredientRepository;

    public IngredientEntity getIngredientEntityById(Long id){

        return ingredientRepository.findIngredientById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public IngredientResponseDto getIngredientById(Long id){

        return ingredientMapper.ingredientEntityToIngredientResponseDto(ingredientRepository.findIngredientById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id)));
    }

    public List<IngredientResponseDto> getAllIngredients() {

        List<IngredientEntity> ingredients = ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return ingredientMapper.ingredientEntityListToIngredientResponseDtoList((ingredients));
    }

    public ErrorsMapDto addIngredient(IngredientRequestDto ingredientRequestDto){
        ErrorsMapDto errors = new ErrorsMapDto(new HashMap<>());
        if( Objects.isNull(ingredientRequestDto.name()) ){
            errors.add("name", new NullValueException("name") );
        }
        if( !containsValidCharacters(ingredientRequestDto.name(), POLISH_ALPHABET) ){
            errors.add("name", new InvalidIngredientNameException());
        }
        if( Objects.isNull(ingredientRequestDto.quantity()) ){
            errors.add("quantity", new NullValueException("quantity"));
        }
        if( errors.isEmpty() ) {
            IngredientEntity ingredientEntity = ingredientMapper.ingredientRequestDtoToIngredientEntity(ingredientRequestDto);
            ingredientRepository.save(ingredientEntity);
        }
        return errors;
    }

    public String deleteIngredientById(Long id){
        ingredientRepository.delete(ingredientRepository.findIngredientById(id)
                        .orElseThrow(() -> new IngredientNotFoundException(id)));
        return "Ingredient number " + id + " has been successfully deleted";
    }

    // TO CHANGE
    public ErrorsMapDto changeIngredientById(Long id, IngredientRequestDto ingredientRequestDto) {

        ErrorsMapDto errors = new ErrorsMapDto(new HashMap<>());

        try {
            IngredientEntity ingredientEntity = getIngredientEntityById(id);
            ingredientEntity.changeName(ingredientRequestDto.name());
            ingredientEntity.changeQuantity(ingredientRequestDto.quantity());
            ingredientEntity.changeUnit(ingredientRequestDto.unit());
        }
        catch(IngredientNotFoundException exception) {
            errors.add("ingredient", new IngredientNotFoundException(id));
        }

        return errors;

    }

}
