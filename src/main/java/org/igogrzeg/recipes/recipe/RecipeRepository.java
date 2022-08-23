package org.igogrzeg.recipes.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    RecipeEntity findRecipeEntityById(Long id);

}
