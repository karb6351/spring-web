package project.services;


import project.models.Lecture;
import project.models.LectureComment;
import project.models.Material;

import java.util.List;

public interface LectureService {

    List<Lecture> getLectures();

    Lecture getLectureById(int id);

    int createLecture(String name);

    void updateLecture(Integer id, String name);

    void updateLecture(Lecture lecture);

    void deleteLecture(Integer id);

    void deleteMaterial(Integer lectureId, Material material);

    void deleteLectureComment(Integer lectureId, LectureComment lectureComment);

    boolean isLectureExist(Integer id);
}
