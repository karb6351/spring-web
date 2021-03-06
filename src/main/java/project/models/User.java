package project.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Vote> votes = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<LectureComment> lectureComments = new HashSet<>();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addRole(UserRole userRole){
        this.userRoles.add(userRole);
    }

    public void addVote(Vote vote){
        this.votes.add(vote);
    }

    public void removeVote(Vote vote){
        this.votes.remove(vote);
    }

    public void removeVoteFromResponse() {
        for (Vote vote: votes){
            vote.getResponse().removeVote(vote);
        }
    }

    @Override
    public String toString(){
        return this.getUsername() + " " + this.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public Set<LectureComment> getLectureComments() {
        return lectureComments;
    }

    public void setLectureComments(Set<LectureComment> lectureComments) {
        this.lectureComments = lectureComments;
    }
}
