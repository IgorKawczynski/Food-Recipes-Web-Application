package org.igogrzeg.recipes.ingredient;

import org.igogrzeg.recipes.ingredient.dtos.IngredientResponseDto;
import org.igogrzeg.recipes.ingredient.exceptions.IngredientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IngredientService {

    private final IngredientMapper ingredientMapper;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService (IngredientMapper ingredientMapper,
                              IngredientRepository ingredientRepository) {
        this.ingredientMapper = ingredientMapper;
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientResponseDto getIngredientById(Long id){
        return ingredientMapper.IngredientEntityToIngredientResponseDto(ingredientRepository.findIngredientById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id)));
    }

    public List<IngredientResponseDto> getAllIngredients() {
        List<IngredientEntity> ingredients = ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return ingredientMapper.IngredientEntityListToIngredientResponseDtoList((ingredients));
    }

    public IngredientResponseDto addIngredient(IngredientEntity ingredient){
        return ingredientMapper.IngredientEntityToIngredientResponseDto(ingredientRepository.save(ingredient));
    }

    public String deleteIngredientById(Long id){
        ingredientRepository
                .delete(ingredientRepository.findIngredientById(id)
                        .orElseThrow(() -> new IngredientNotFoundException(id)));
        return "Ingredient number " + id + "has been successfully deleted";
    }


}
