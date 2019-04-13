package project.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "responses")
public class Response implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String response;

    @Column(insertable = false, updatable = false, name = "question_id")
    private Integer questionId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "response", fetch = FetchType.EAGER)
    private Set<Vote> votes;

    public Response(){}

    public Response(String response){
        this.response = response;
    }

    public Response(String response, Question question){
        this.response = response;
        this.question = question;
    }

    @Override
    public String toString(){
        return this.response;
    }

    public void addVote(Vote vote){
        this.votes.add(vote);
    }

    public void removeVote(Vote vote){
        this.votes.remove(vote);
    }

    public void removeVoteFromUser() {
        for (Vote vote: votes){
            vote.getUser().removeVote(vote);
        }
    }

    public Integer countVote(){
        return this.votes.size();
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
