package org.igogrzeg.recipes.interfaces;

public interface Validator {

    String POLISH_ALPHABET = "[a-zA-Z-\\p{IsAlphabetic}]+";

    String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
            "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
            "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\" +
            "x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";

    String ENGLISH_LETTERS_NUMBERS_SPECIAL_CHARACTERS = "[\\x21-\\x7E]+";


    default boolean containsValidCharacters(String stringToCheck, String pattern) {
        return stringToCheck.matches(pattern);
    }

    default boolean containsPolishCharacters(String stringToCheck) {
        return stringToCheck.matches(POLISH_ALPHABET);
    }

    default boolean isValidLength(String stringToCheck, int minimumLength, int maximumLength) {
        return stringToCheck.length() > minimumLength - 1 && stringToCheck.length() < maximumLength + 1;
    }

    default boolean isValidNumber(Integer numberToCheck, int minimumLength, int maximumLength) {
        return numberToCheck > minimumLength - 1 && numberToCheck < maximumLength + 1;
    }

}