package org.igogrzeg.recipes.user.exceptions;

public class NoSuchUserExists extends RuntimeException {

    public NoSuchUserExists(Long id){
        super("User with id: " + id + " doesn't exists!");
    }

    public NoSuchUserExists(String email){
        super("User with email: " + email + " doesn't exists!");
    }
}
