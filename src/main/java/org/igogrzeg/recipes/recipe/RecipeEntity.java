package org.igogrzeg.recipes.recipe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.basic.BasicEntity;
import org.igogrzeg.recipes.ingredient.IngredientEntity;
import org.igogrzeg.recipes.recipe.enums.CuisineType;
import org.igogrzeg.recipes.recipe.enums.Difficulty;
import org.igogrzeg.recipes.recipe.enums.MealType;
import org.igogrzeg.recipes.recipe.valueObjects.InformationValidator;
import org.igogrzeg.recipes.recipe.valueObjects.NameValidator;
import org.igogrzeg.recipes.recipe.valueObjects.PreparationTimeValidator;
import org.igogrzeg.recipes.user.UserEntity;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "RECIPES")
public class RecipeEntity extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity userId;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<IngredientEntity> ingredients;

    @Embedded
    private NameValidator name;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "information", column = @Column(name = "description"))
    })
    private InformationValidator description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "information", column = @Column(name = "instruction"))
    })
    private InformationValidator instruction;

    @Embedded
    private PreparationTimeValidator preparationTime;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @Enumerated(EnumType.STRING)
    private CuisineType cuisineType;

    @Builder
    public RecipeEntity(UserEntity userId, List<IngredientEntity> ingredients,
                        NameValidator name, InformationValidator description,
                        InformationValidator instruction, PreparationTimeValidator preparationTime,
                        Difficulty difficulty, MealType mealType, CuisineType cuisineType) {

        this.userId = userId;
        this.ingredients = ingredients;
        this.name = name;
        this.description = description;
        this.instruction = instruction;
        this.preparationTime = preparationTime;
        this.difficulty = difficulty;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
    }

    public void changeName(String name) {
        this.name = new NameValidator(name);
    }

    public void changeDescription(String description) {
        this.description = new InformationValidator(description);
    }

    public void changeInstruction(String instruction) {
        this.instruction = new InformationValidator(instruction);
    }

    public void changePreparationTime(Integer preparationTime) {
        this.preparationTime = new PreparationTimeValidator(preparationTime);
    }

    public void changeDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void changeMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public void changeCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }
}
