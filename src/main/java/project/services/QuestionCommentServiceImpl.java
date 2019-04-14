package project.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.exceptions.QuestionCommentNotFoundException;
import project.models.*;
import project.repositories.QuestionCommentRepository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class QuestionCommentServiceImpl implements QuestionCommentService {

    @Resource
    private QuestionCommentRepository questionCommentRepository;

    @Override
    @Transactional
    public List<QuestionComment> getQuestionComments() {
        return questionCommentRepository.findAll();
    }

    @Override
    @Transactional
    public List<QuestionComment> getQuestionCommentsByLectureIdSortByDate(Integer id){
        return questionCommentRepository.findAllByQuestionIdOrderByCreatedAtDesc(id);
    }

    @Override
    @Transactional
    public QuestionComment getQuestionCommentById(int id) {
        return questionCommentRepository.findOne(id);
    }

    @Override
    @Transactional
    public int createQuestionComment(String content, Question question, User user) {
        QuestionComment questionComment = new QuestionComment(content);
        questionComment.setQuestion(question);
        questionComment.setUser(user);
        questionComment.setCreatedAt(new Date());
        return questionCommentRepository.save(questionComment).getId();
    }

    @Override
    @Transactional
    public void updateQuestionComment(Integer id, String content) throws QuestionCommentNotFoundException {
        QuestionComment questionComment = questionCommentRepository.findOne(id);
        if (questionComment == null){
            throw new QuestionCommentNotFoundException("Comment not found");
        }
        questionComment.setContent(content);
        questionCommentRepository.save(questionComment);
    }

    @Override
    @Transactional
    public void deleteQuestionComment(Integer id) throws QuestionCommentNotFoundException {
        QuestionComment questionComment = questionCommentRepository.findOne(id);
        if (questionComment == null){
            throw new QuestionCommentNotFoundException("Comment not found");
        }
        questionCommentRepository.delete(questionComment);
    }
}
