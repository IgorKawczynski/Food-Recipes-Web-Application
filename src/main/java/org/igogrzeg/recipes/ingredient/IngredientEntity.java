package org.igogrzeg.recipes.ingredient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.basic.BasicEntity;
import org.igogrzeg.recipes.ingredient.enums.Unit;
import org.igogrzeg.recipes.ingredient.valueObjects.QuantityValidator;
import org.igogrzeg.recipes.recipe.RecipeEntity;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "INGREDIENTS")
public class IngredientEntity extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonBackReference
    private RecipeEntity recipeId;

    @Embedded
    private NameValidator name;

    @Embedded
    private QuantityValidator quantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Builder
    public IngredientEntity(RecipeEntity recipeId, NameValidator name, QuantityValidator quantity, Unit unit) {
        this.recipeId = recipeId;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public void changeName(String name) {
        this.name = new NameValidator(name);
    }

    public void changeQuantity(Integer quantity) {
        this.quantity = new QuantityValidator(quantity);
    }

    public void changeUnit(Unit unit) {
        this.unit = unit;
    }


}
