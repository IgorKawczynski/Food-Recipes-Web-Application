package org.igogrzeg.recipes.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    Optional<RecipeEntity> findRecipeById(Long id);

    RecipeEntity findRecipeEntityById(Long id);

}
