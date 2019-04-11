package project.models;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Comment commentTarget;

    @Any(metaColumn=@Column(name="target_type" , length=3), fetch= FetchType.LAZY)
    @AnyMetaDef(
            idType="int", metaType="string" ,
            metaValues={
                    @MetaValue(targetEntity=Lecture.class, value="LECTURE" ),

            }
    )

    @Cascade( { org.hibernate.annotations.CascadeType.ALL } )
    @JoinColumn(name="target_id")
    public Comment getCommentTarget() {
        return this.commentTarget;
    }

    public void setCommentTarget(Comment commentTarget) {
        this.commentTarget = commentTarget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
