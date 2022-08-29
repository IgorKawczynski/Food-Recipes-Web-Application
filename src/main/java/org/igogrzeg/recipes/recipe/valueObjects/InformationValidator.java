package org.igogrzeg.recipes.recipe.valueObjects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class InformationValidator {

    @Column
    private String information;


    public InformationValidator(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return information;
    }
}
