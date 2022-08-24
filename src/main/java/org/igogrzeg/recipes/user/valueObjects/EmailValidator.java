package org.igogrzeg.recipes.user.valueObjects;

import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.interfaces.Validator;

import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
            "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
            "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\" +
            "x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
    private String email;

    public EmailValidator(String newEmail) {
        if ( Objects.isNull(newEmail) ) {
            throw new IllegalStateException("Email can't be null!");
        }
        if ( !isValidLength(newEmail, 5, 80) ) {
            throw new IllegalArgumentException("Email must be between 5 and 80 chars length!");
        }
        if ( !containsValidCharacters(newEmail, EMAIL_PATTERN) ) {
            throw new IllegalArgumentException("Email may contain only letters, digits, and '@' '.' signs!");
        }
        email = newEmail;
    }

    @Override
    public boolean containsValidCharacters(String stringToCheck, String pattern) {
        stringToCheck = stringToCheck.toLowerCase();
        return Validator.super.containsValidCharacters(stringToCheck, pattern);
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailValidator emailValidator1 = (EmailValidator) o;
        return email.equals(emailValidator1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
