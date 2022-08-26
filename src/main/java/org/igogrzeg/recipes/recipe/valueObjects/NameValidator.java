package org.igogrzeg.recipes.recipe.valueObjects;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.interfaces.Validator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class NameValidator implements Validator{

    private static final String POLISH_ALPHABET = "[a-zA-Z-\\p{IsAlphabetic}]+";

    @Column
    private String name;

    public NameValidator(String name) {
//        if(Objects.isNull(name))
//            throw new IllegalArgumentException("NAME CANNOT BE NULL !!");
//        if(!containsValidCharacters(name, POLISH_ALPHABET))
//            throw new IllegalStateException("BRAND NAME MAY CONTAIN ONLY LETTERS OR NUMBERS !!");
//        if(!isValidLength(name, 1, 20))
//            throw new IllegalStateException("BRAND NAME MUST BE BETWEEN 1 AND 20 CHARACTERS !!");
        this.name = name;
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
