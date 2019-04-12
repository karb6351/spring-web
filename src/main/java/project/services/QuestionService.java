package project.services;

import project.models.Question;
import project.models.Response;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions();

    Question getQuestionById(Integer id);

    int createQuestion(String question);

    void updateQuestion(Integer id, String question);

    void deleteQuestion(Integer id);

    Question deleteResponse(Response response);

}
