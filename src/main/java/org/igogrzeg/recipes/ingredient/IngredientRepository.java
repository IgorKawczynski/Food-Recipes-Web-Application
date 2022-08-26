package org.igogrzeg.recipes.ingredient;

import org.igogrzeg.recipes.recipe.RecipeEntity;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    Optional<IngredientEntity> findIngredientById(Long id);

    // placeholder -- to change
    IngredientEntity findDistinctByName(NameValidator name);

}
