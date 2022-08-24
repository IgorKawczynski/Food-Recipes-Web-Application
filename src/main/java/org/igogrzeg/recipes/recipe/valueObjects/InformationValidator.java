package org.igogrzeg.recipes.recipe.valueObjects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class InformationValidator {

    private static final String POLISH_ALPHABET = "[a-zA-Z-\\p{IsAlphabetic}]+";

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
