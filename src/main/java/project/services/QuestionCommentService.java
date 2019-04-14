package project.services;

import project.exceptions.QuestionCommentNotFoundException;
import project.models.Question;
import project.models.QuestionComment;
import project.models.User;

import java.util.List;

public interface QuestionCommentService {

    List<QuestionComment> getQuestionComments();

    List<QuestionComment> getQuestionCommentsByLectureIdSortByDate(Integer id);

    QuestionComment getQuestionCommentById(int id);

    int createQuestionComment(String content, Question question, User user);

    void updateQuestionComment(Integer id, String content) throws QuestionCommentNotFoundException;

    void deleteQuestionComment(Integer id) throws QuestionCommentNotFoundException ;

}
