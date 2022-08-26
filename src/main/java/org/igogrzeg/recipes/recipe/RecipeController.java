package org.igogrzeg.recipes.recipe;

import org.igogrzeg.recipes.recipe.dtos.RecipeRequestDto;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponseDto getRecipeById (@PathVariable Long id) {
        return recipeService.getRecipeResponseDtoById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeRequestDto addRecipe (@RequestBody RecipeEntity recipeEntity) {
        return recipeService.addRecipe(recipeEntity);
    }

}
