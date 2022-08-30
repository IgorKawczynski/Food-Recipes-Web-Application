package org.igogrzeg.recipes.recipe.valueObjects;

import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.interfaces.Validator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class NameValidator implements Validator{

    private static final String POLISH_ALPHABET = "[a-zA-Z-\\p{IsAlphabetic}]+";

    @Column
    private String name;

    public NameValidator(String name) {
        if( Objects.isNull(name) )
            throw new IllegalArgumentException("NAME CANNOT BE NULL !!");
        if( !containsValidCharacters(name, POLISH_ALPHABET) )
            throw new IllegalStateException("NAME MAY CONTAIN ONLY LETTERS OR NUMBERS !!");
        if( !isValidLength(name, 1, 36) )
            throw new IllegalStateException("NAME MUST BE BETWEEN 1 AND 36 CHARACTERS !!");
        this.name = "TOP " + name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameValidator that = (NameValidator) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
