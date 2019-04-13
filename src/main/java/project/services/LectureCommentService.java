package project.services;

import project.exceptions.LectureCommentNotFoundException;
import project.models.Lecture;
import project.models.LectureComment;
import project.models.User;

import java.util.List;

public interface LectureCommentService {

    List<LectureComment> getLectureComments();

    List<LectureComment> getLectureCommentsByLectureIdSortByDate(Integer id);

    LectureComment getLectureCommentById(int id);

    int createLectureComment(String content, Lecture lecture, User user);

    void updateLectureComment(Integer id, String content) throws LectureCommentNotFoundException;

    void deleteLectureComment(Integer id) throws LectureCommentNotFoundException ;

}
