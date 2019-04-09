package project.exceptions;

public class DuplicateUserException extends Exception {

    protected String message;

    public DuplicateUserException(String message){
        this.message = message;
    }

}
