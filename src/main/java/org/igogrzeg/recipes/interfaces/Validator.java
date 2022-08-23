package org.igogrzeg.recipes.interfaces;

public interface Validator {

    default boolean containsValidCharacters(String stringToCheck, String pattern) {
        return stringToCheck.matches(pattern);
    }

    default boolean isValidLength(String stringToCheck, int minimumLength, int maximumLength) {
        return stringToCheck.length() > minimumLength - 1 && stringToCheck.length() < maximumLength + 1;
    }

}