package project.exceptions;

public class VoteSameResponseException extends Exception {

    private String message;

    public VoteSameResponseException(){};

    public VoteSameResponseException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
