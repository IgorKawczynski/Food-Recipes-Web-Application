package org.igogrzeg.recipes.favorite_recipe;

import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.basic.BasicEntity;
import org.igogrzeg.recipes.recipe.RecipeEntity;
import org.igogrzeg.recipes.user.UserEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "FAVORITE_RECIPES")
public class FavoriteRecipe extends BasicEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeEntity recipeId;

    public FavoriteRecipe(UserEntity user, RecipeEntity recipe) {
        this.userId = user;
        this.recipeId = recipe;
    }

}
