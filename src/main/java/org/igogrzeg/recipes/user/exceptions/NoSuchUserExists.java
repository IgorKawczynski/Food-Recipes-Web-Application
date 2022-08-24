package org.igogrzeg.recipes.user.exceptions;

public class NoSuchUserExists extends RuntimeException {

    public NoSuchUserExists(Long id){
        super("User with id: " + id + "doesn't exists!");
    }

}
