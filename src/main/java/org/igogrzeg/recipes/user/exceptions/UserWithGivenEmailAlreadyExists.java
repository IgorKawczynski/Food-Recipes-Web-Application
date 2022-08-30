package org.igogrzeg.recipes.user.exceptions;

public class UserWithGivenEmailAlreadyExists extends RuntimeException{

    private static final String MESSAGE = "User with given email is already registered!";

    public UserWithGivenEmailAlreadyExists() {
        super(MESSAGE);
    }

}
