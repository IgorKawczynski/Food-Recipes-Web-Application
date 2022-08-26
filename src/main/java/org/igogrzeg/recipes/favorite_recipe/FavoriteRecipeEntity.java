package org.igogrzeg.recipes.favorite_recipe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.igogrzeg.recipes.basic.BasicEntity;
import org.igogrzeg.recipes.recipe.RecipeEntity;
import org.igogrzeg.recipes.user.UserEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FAVORITE_RECIPES")
public class FavoriteRecipeEntity extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonBackReference
    private RecipeEntity recipeEntity;

    public FavoriteRecipeEntity(UserEntity userEntity, RecipeEntity recipeEntity) {
        this.userEntity = userEntity;
        this.recipeEntity = recipeEntity;
    }

}
