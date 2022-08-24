package org.igogrzeg.recipes.favorite_recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsListDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/favoriterecipes")
public class FavoriteRecipeController {

    private final FavoriteRecipeService favoriteRecipeService;

    @PostMapping("/add")
    public ErrorsListDto addRecipeToFavoriteList(Long userID, Long recipeID) {
        return favoriteRecipeService.addRecipeToFavoriteList(userID, recipeID);
    }

}
