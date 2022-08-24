package org.igogrzeg.recipes.ingredient;

import org.igogrzeg.recipes.ingredient.dtos.IngredientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientResponseDto> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }
}
