package org.igogrzeg.recipes.recipe.valueObjects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class NameValidator {

    @Column
    private String name;

    public NameValidator(String name) {
        this.name = name;
    }
}
