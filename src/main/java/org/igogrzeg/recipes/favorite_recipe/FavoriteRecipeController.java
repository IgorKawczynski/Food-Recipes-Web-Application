package org.igogrzeg.recipes.favorite_recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.favorite_recipe.dtos.FavoriteRecipeDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/favoriterecipes")
public class FavoriteRecipeController {

    private final FavoriteRecipeService favoriteRecipeService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsMapDto addRecipeToFavoriteList(@RequestBody FavoriteRecipeDto favoriteRecipe) {
        return favoriteRecipeService.addRecipeToFavoriteList(favoriteRecipe);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeResponseDto> getFavoriteRecipesByUserId(@PathVariable Long userId) {
        return favoriteRecipeService.getFavoriteRecipesByUserId(userId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteRecipeDto> getAllFavoriteRecipes(){
        return favoriteRecipeService.getAll();
    }

}
