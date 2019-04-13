package project.exceptions;

public class LectureCommentNotFoundException extends Exception {
    private String message ;

    public LectureCommentNotFoundException(){ }

    public LectureCommentNotFoundException(String message){
        this.message = message;
    }

    public String toString(){
        return this.message;
    }
}
