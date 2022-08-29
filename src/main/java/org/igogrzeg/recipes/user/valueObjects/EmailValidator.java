package org.igogrzeg.recipes.user.valueObjects;

import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.interfaces.Validator;

import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class EmailValidator implements Validator {

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
