package project.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "lectures")
public class Lecture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "title" )
    private String name;

    @OneToMany(mappedBy = "lecture", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Material> materials = new HashSet<>();

    @OneToMany(mappedBy = "lecture", fetch = FetchType.EAGER)
    private Set<LectureComment> lectureComments = new HashSet<>();

    public Lecture(){}

    public Lecture(String name){
        this.name = name;
    }

    public void removeMaterial(Material material){
        this.materials.remove(material);
    }

    public void removeLectureComment(LectureComment lectureComment){
        this.lectureComments.remove(lectureComment);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    public Set<LectureComment> getLectureComments() {
        return lectureComments;
    }

    public void setLectureComments(Set<LectureComment> lectureComments) {
        this.lectureComments = lectureComments;
    }
}
