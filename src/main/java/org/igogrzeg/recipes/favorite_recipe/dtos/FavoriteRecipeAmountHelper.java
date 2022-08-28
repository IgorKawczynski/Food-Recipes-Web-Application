package org.igogrzeg.recipes.favorite_recipe.dtos;

import lombok.Builder;
import org.igogrzeg.recipes.recipe.RecipeEntity;

//
// class made only for gathering data from getMostFavoriteRecipesLists()
// method from FavoriteRecipeRepository class, shouldn't be used to
// forwarded to the frontend, use FavoriteRecipeAmountDto either
//

public record FavoriteRecipeAmountHelper( RecipeEntity recipe, Long amount) {

    @Builder
    public FavoriteRecipeAmountHelper { }

}
