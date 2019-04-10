package project.exceptions;

public class LectureNotFoundException extends Exception {
    private String message;
    public LectureNotFoundException(String message){
        this.message = message;
    }
}
