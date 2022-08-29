package org.igogrzeg.recipes.ingredient;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.ingredient.dtos.IngredientRequestDto;
import org.igogrzeg.recipes.ingredient.dtos.IngredientResponseDto;
import org.igogrzeg.recipes.ingredient.valueObjects.QuantityValidator;
import org.igogrzeg.recipes.recipe.RecipeRepository;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IngredientMapper {

    private final RecipeRepository recipeRepository;


    public IngredientRequestDto ingredientEntityToIngredientRequestDto(IngredientEntity ingredient) {
        return IngredientRequestDto
                .builder()
                .recipeId(ingredient.getRecipeEntity().getId())
                .name(ingredient.getName().toString())
                .quantity(ingredient.getQuantity().toInteger())
                .unit(ingredient.getUnit())
                .build();
    }

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

    public List<IngredientRequestDto> ingredientEntityListToIngredientRequestDtoList(List<IngredientEntity> ingredients){
        return ingredients
                .stream()
                .map(this::ingredientEntityToIngredientRequestDto)
                .collect(Collectors.toList());
    }

    public IngredientEntity ingredientRequestDtoToIngredientEntity (IngredientRequestDto ingredientRequestDto) {
        return IngredientEntity
                .builder()
                .recipeEntity(recipeRepository.findRecipeEntityById(ingredientRequestDto.recipeId()))
                .name(new NameValidator(ingredientRequestDto.name()))
                .quantity(new QuantityValidator(ingredientRequestDto.quantity()))
                .unit(ingredientRequestDto.unit())
                .build();
    }

    public List<IngredientEntity> ingredientRequestDtoListToIngredientEntityList (List<IngredientRequestDto> ingredients) {
        return ingredients
                .stream()
                .map(this::ingredientRequestDtoToIngredientEntity)
                .collect(Collectors.toList());
    }
}
