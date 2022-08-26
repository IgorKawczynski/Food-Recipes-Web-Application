package org.igogrzeg.recipes.favorite_recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipeEntity, Long> {

    List<FavoriteRecipeEntity> findAllByUserEntityId(Long userId);

    @Query(value = "SELECT CASE WHEN COUNT(id) > 0 THEN true ELSE false END " +
                   "FROM FAVORITE_RECIPES AS fre WHERE fre.user_id = ?1 AND fre.recipe_id = ?2", nativeQuery = true)
    Boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);

}
