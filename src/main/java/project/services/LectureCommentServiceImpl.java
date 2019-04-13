package project.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.exceptions.LectureCommentNotFoundException;
import project.models.Lecture;
import project.models.LectureComment;
import project.models.User;
import project.repositories.LectureCommentRepository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class LectureCommentServiceImpl implements LectureCommentService {

    @Resource
    private LectureCommentRepository lectureCommentRepository;

    @Override
    @Transactional
    public List<LectureComment> getLectureComments() {
        return lectureCommentRepository.findAll();
    }

    @Override
    @Transactional
    public List<LectureComment> getLectureCommentsByLectureIdSortByDate(Integer id){
        return lectureCommentRepository.findAllByLectureIdOrderByCreatedAtDesc(id);
    }

    @Override
    @Transactional
    public LectureComment getLectureCommentById(int id) {
        return lectureCommentRepository.findOne(id);
    }

    @Override
    @Transactional
    public int createLectureComment(String content, Lecture lecture, User user) {
        LectureComment lectureComment = new LectureComment(content);
        lectureComment.setLecture(lecture);
        lectureComment.setUser(user);
        lectureComment.setCreatedAt(new Date());
        return lectureCommentRepository.save(lectureComment).getId();
    }

    @Override
    @Transactional
    public void updateLectureComment(Integer id, String content) throws LectureCommentNotFoundException {
        LectureComment lectureComment = lectureCommentRepository.findOne(id);
        if (lectureComment == null){
            throw new LectureCommentNotFoundException("Comment not found");
        }
        lectureComment.setContent(content);
        lectureCommentRepository.save(lectureComment);
    }

    @Override
    @Transactional
    public void deleteLectureComment(Integer id) throws LectureCommentNotFoundException {
        LectureComment lectureComment = lectureCommentRepository.findOne(id);
        if (lectureComment == null){
            throw new LectureCommentNotFoundException("Comment not found");
        }
        lectureCommentRepository.delete(lectureComment);
    }
}
