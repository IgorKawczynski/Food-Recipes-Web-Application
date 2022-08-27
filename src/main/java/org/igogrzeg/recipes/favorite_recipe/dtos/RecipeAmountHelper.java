package org.igogrzeg.recipes.favorite_recipe.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import org.igogrzeg.recipes.recipe.RecipeEntity;

//
// class made only for gathering data from getMostFavoriteRecipesLists()
// method from FavoriteRecipeRepository class, shouldn't be used to
// forwarded to the frontend, use RecipeAmountDto either
//
public record RecipeAmountHelper(@JsonBackReference @Getter RecipeEntity recipe, Long amount) {

    @Builder
    public RecipeAmountHelper { }

}
