package org.igogrzeg.recipes.favorite_recipe;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.favorite_recipe.dtos.FavoriteRecipeDto;
import org.igogrzeg.recipes.favorite_recipe.dtos.RecipeAmountHelper;
import org.igogrzeg.recipes.favorite_recipe.exceptions.RecipeAlreadyInFavoriteRecipesList;
import org.igogrzeg.recipes.recipe.RecipeService;
import org.igogrzeg.recipes.recipe.dtos.RecipeResponseDto;
import org.igogrzeg.recipes.recipe.exceptions.RecipeNotFoundException;
import org.igogrzeg.recipes.user.UserService;
import org.igogrzeg.recipes.user.exceptions.NoSuchUserExists;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FavoriteRecipeService {

    private final FavoriteRecipeRepository favoriteRecipeRepository;
    private final FavoriteRecipeMapper favoriteRecipeMapper;
    private final RecipeService recipeService;
    private final UserService userService;

    public ErrorsMapDto addRecipeToFavoriteList(FavoriteRecipeDto favoriteRecipe) {
        var errorsMapDto = new ErrorsMapDto(new HashMap<String, String>());

        try {
            var user = userService.getUserEntityById(favoriteRecipe.userId());
            var recipe = recipeService.getRecipeEntityById(favoriteRecipe.recipeId());
            var favoriteRecipeToBeAdded = new FavoriteRecipeEntity(user, recipe);
            saveRecipeInFavoriteRecipesTable(favoriteRecipeToBeAdded);
        } catch ( NoSuchUserExists exception ) {
            errorsMapDto.add( new NoSuchUserExists(favoriteRecipe.userId()).getMessage(), "to be implemented" );
        } catch ( RecipeNotFoundException exception ) {
            errorsMapDto.add( new RecipeNotFoundException(favoriteRecipe.recipeId()).getMessage(), "to be implemented" );
        } catch ( RecipeAlreadyInFavoriteRecipesList exception ) {
            errorsMapDto.add( new RecipeAlreadyInFavoriteRecipesList().getMessage(), "to be implemented");
        }

       return errorsMapDto;
    }

    private void saveRecipeInFavoriteRecipesTable(FavoriteRecipeEntity favoriteRecipeEntity) {
        if ( isAlreadyInDatabase(favoriteRecipeEntity) ) {
            throw new RecipeAlreadyInFavoriteRecipesList();
        }
        favoriteRecipeRepository.save(favoriteRecipeEntity);
    }

    public Boolean isAlreadyInDatabase(FavoriteRecipeEntity favoriteRecipe) {
        return favoriteRecipeRepository
                .existsByUserIdAndRecipeId(favoriteRecipe.getUserEntity().getId(), favoriteRecipe.getRecipeEntity().getId());
    }

    public List<RecipeResponseDto> getFavoriteRecipesByUserId(Long userId) {
        var favoriteRecipesIds = getFavoriteRecipesIdsByUserId(userId);
        return recipeService.getRecipeResponseDtosByIds(favoriteRecipesIds);
    }

    public List<Long> getFavoriteRecipesIdsByUserId(Long userId) {
        var userFavoriteRecipes = favoriteRecipeRepository.findAllByUserEntityId(userId);
        return userFavoriteRecipes.stream()
                .map(favoriteRecipe -> favoriteRecipe.getRecipeEntity().getId())
                .collect(Collectors.toList());
    }

    public List<RecipeAmountHelper> getMostFavoriteRecipes() {
        return favoriteRecipeRepository.getMostFavoriteRecipesLists();
    }

    public List<FavoriteRecipeDto> getAll() {
        var favoriteRecipes = favoriteRecipeRepository.findAll();
        return favoriteRecipeMapper.favoriteRecipeEntitiesToFavoriteRecipeRequestDtoList(favoriteRecipes);
    }

}
