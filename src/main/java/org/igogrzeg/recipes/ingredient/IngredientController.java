package org.igogrzeg.recipes.ingredient;

import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.ingredient.dtos.IngredientRequestDto;
import org.igogrzeg.recipes.ingredient.dtos.IngredientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientResponseDto getIngredientById(@PathVariable Long id) {
        return ingredientService.getIngredientById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsMapDto addIngredient(@RequestBody IngredientRequestDto ingredientRequestDto) {
        return ingredientService.addIngredient(ingredientRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsMapDto changeIngredientById(@PathVariable Long id, @RequestBody IngredientRequestDto ingredientRequestDto) {
        return ingredientService.changeIngredientById(id, ingredientRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteIngredientById(@PathVariable Long id) {
        return ingredientService.deleteIngredientById(id);
    }
}
