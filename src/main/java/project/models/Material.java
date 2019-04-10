package project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "materials")
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filename;

    @Column(name = "content_type")
    private String contentType;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] content;

    @Column(name = "lecture_id", insertable=false, updatable=false)
    private Integer lectureId;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public Material(){}

    public Material(String filename, byte[] content, String contentType, Lecture lecture){
        this.filename = filename;
        this.content = content;
        this.contentType = contentType;
        this.lectureId = lecture.getId();
        this.lecture = lecture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String content_type) {
        this.contentType = content_type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
