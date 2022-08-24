package org.igogrzeg.recipes.recipe;

import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeResponseDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

}
