package project.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question implements Serializable {

    public static final int LIMITED_RESPONSE = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Response> responses = new HashSet<>();

    public Question(){}

    public Question(String question){
        this.question = question;
    }

    public void removeResponse(Response response){
        responses.remove(response);
    }

    public String[] getResponseName(){
        String[] responseName = new String[this.responses.size()];
        int index = 0;
        for(Response response: this.responses){
            responseName[index++] = response.getResponse();
        }
        return responseName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }
}
