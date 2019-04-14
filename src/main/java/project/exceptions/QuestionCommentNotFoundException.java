package project.exceptions;

public class QuestionCommentNotFoundException extends Exception {
    private String message ;

    public QuestionCommentNotFoundException(){ }

    public QuestionCommentNotFoundException(String message){
        this.message = message;
    }

    public String toString(){
        return this.message;
    }
}
