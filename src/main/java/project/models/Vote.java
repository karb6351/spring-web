package project.models;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "user_id", insertable = false, updatable = false)
//    private Integer userId;
//
//    @Column(name = "response_id", insertable = false, updatable = false)
//    private Integer responseId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;

    public Vote(){}

    public Vote(User user, Response response){
        this.user = user;
        this.response = response;
    }

    public String toString(){
        return this.user.getId() + " " + this.response.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Integer getResponseId() {
//        return responseId;
//    }
//
//    public void setResponseId(Integer responseId) {
//        this.responseId = responseId;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
