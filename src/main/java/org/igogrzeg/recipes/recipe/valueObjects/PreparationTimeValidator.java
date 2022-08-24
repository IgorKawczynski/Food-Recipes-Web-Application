package org.igogrzeg.recipes.recipe.valueObjects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class PreparationTimeValidator {

    @Column
    private int preparationTime;

    public PreparationTimeValidator(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }
}
