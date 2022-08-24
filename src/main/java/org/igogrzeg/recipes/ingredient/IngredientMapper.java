package org.igogrzeg.recipes.ingredient;

import org.igogrzeg.recipes.ingredient.dtos.IngredientResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {

    public IngredientResponseDto ingredientEntityToIngredientResponseDto(IngredientEntity ingredient) {
        return IngredientResponseDto
                .builder()
                .name(ingredient.getName().toString())
                .quantity(ingredient.getQuantity().toInteger())
                .unit(ingredient.getUnit())
                .build();
    }

    public List<IngredientResponseDto> ingredientEntityListToIngredientResponseDtoList(List<IngredientEntity> ingredients){
        return ingredients
                .stream()
                .map(this::ingredientEntityToIngredientResponseDto)
                .collect(Collectors.toList());
    }
}
