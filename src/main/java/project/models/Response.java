package project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "responses")
public class Response implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String response;

    @Column(insertable = false, updatable = false, name = "question_id")
    private Integer questionId;

//    @Column(insertable = false, updatable = false, name = "user_id")
//    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public Response(){}

    public Response(String response){
        this.response = response;
    }

    public Response(String response, Question question){
        this.response = response;
        this.question = question;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
