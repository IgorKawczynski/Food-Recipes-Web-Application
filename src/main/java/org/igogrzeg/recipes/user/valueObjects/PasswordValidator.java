package org.igogrzeg.recipes.user.valueObjects;

import lombok.NoArgsConstructor;
import org.igogrzeg.recipes.interfaces.Validator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class PasswordValidator implements Validator {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String password;

    public PasswordValidator(String password) {

        if ( Objects.isNull(password) )
            throw new IllegalStateException("Password cant be null!");

        if ( !isValidLength(password, 7, 28) )
            throw new IllegalStateException("Password must be between 7 and 28 characters length!");

        if ( !containsValidCharacters(password, ENGLISH_LETTERS_NUMBERS_SPECIAL_CHARACTERS) )
            throw new IllegalStateException("Password may contain only english letters, numbers and special characters!");

        password = encryptPassword(password);
        this.password = password;
    }

    private String encryptPassword(String password){
        return this.password = passwordEncoder.encode(password);
    }

    @Override
    public String toString() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordValidator passwordValidator1 = (PasswordValidator) o;
        return password.equals(passwordValidator1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

}
