package project.exceptions;

public class FullResponseException extends Exception {

    private String message;

    public FullResponseException(){
        this.message = "This question has 4 response";
    }

    public FullResponseException(String message){
        this.message = message;
    }
}
