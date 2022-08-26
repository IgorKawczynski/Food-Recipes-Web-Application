package org.igogrzeg.recipes.ingredient.dtos;

import com.sun.istack.NotNull;
import org.igogrzeg.recipes.ingredient.enums.Unit;
import org.igogrzeg.recipes.ingredient.valueObjects.QuantityValidator;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record IngredientRequestDto(
        Long recipeId,
        NameValidator name,
        QuantityValidator quantity,
        Unit unit
        ) {



}
