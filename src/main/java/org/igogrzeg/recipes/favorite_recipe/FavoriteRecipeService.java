package org.igogrzeg.recipes.favorite_recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsListDto;
import org.igogrzeg.recipes.recipe.RecipeService;
import org.igogrzeg.recipes.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class FavoriteRecipeService {

    private final FavoriteRecipeRepository favoriteRecipeRepository;
    private final RecipeService recipeService;
    private final UserService userService;

    public ErrorsListDto addRecipeToFavoriteList(Long userId, Long recipeId) {
        var errorsListDto = new ErrorsListDto(new ArrayList<>());
        var user = userService.getUserEntityById(userId);
        var recipe = recipeService.getRecipeEntityById(recipeId);
        var favoriteRecipe = new FavoriteRecipe(user, recipe);
       favoriteRecipeRepository.save(favoriteRecipe);

       return errorsListDto;
    }

}
