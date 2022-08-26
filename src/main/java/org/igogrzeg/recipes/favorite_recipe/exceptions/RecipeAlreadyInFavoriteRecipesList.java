package org.igogrzeg.recipes.favorite_recipe.exceptions;

public class RecipeAlreadyInFavoriteRecipesList extends RuntimeException{

    private final static String MESSAGE = "This recipe is already in yours favorite recipes list!";

    public RecipeAlreadyInFavoriteRecipesList(){
        super(MESSAGE);
    }

}
