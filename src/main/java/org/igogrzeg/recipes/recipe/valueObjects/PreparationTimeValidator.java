package org.igogrzeg.recipes.recipe.valueObjects;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@ToString
@Embeddable
@NoArgsConstructor
public class PreparationTimeValidator {

    @Column
    private Integer preparationTime;

    public PreparationTimeValidator(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer toInteger() {
        return preparationTime;
    }
}
