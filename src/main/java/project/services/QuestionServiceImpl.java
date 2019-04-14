package project.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.models.Question;
import project.models.QuestionComment;
import project.models.Response;
import project.repositories.QuestionRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    QuestionRepository questionRepository;

    @Override
    @Transactional
    public List<Question> getQuestions(){
        return this.questionRepository.findAll();
    }

    @Override
    @Transactional
    public Question getQuestionById(Integer id){
        return this.questionRepository.findOne(id);
    }

    @Override
    @Transactional
    public int createQuestion(String question){
        Question q = this.questionRepository.save(new Question(question));
        return q.getId();
    }

    @Override
    @Transactional
    public void updateQuestion(Integer id, String question){
        Question q = this.getQuestionById(id);
        if (q != null){
            q.setQuestion(question);
            this.questionRepository.save(q);
        }
    }

    @Override
    @Transactional
    public void deleteQuestion(Integer id){
        Question q = this.getQuestionById(id);
        if (q != null){
            this.questionRepository.delete(q);
        }
    }

    @Override
    @Transactional
    public  Question deleteResponse(Response response){
        Question question = response.getQuestion();
        List<Response> removeList = new ArrayList<>();
        for(Response buffer: question.getResponses()){
            if (buffer.getId().equals(response.getId())){
                removeList.add(buffer);
            }
        }
        for(Response buffer: removeList){
            question.removeResponse(buffer);
            buffer.removeVoteFromUser();
        }
        questionRepository.save(question);
        return question;
    }

    @Override
    @Transactional
    public void deleteQuestionComment(Integer lectureId, QuestionComment questionComment){
        Question question = questionRepository.findOne(lectureId);
        List<QuestionComment> removeList = new ArrayList<>();
        for(QuestionComment buffer: question.getQuestionComments()){
            if (buffer.getId().equals(questionComment.getId())){
                removeList.add(buffer);
            }
        }
        for(QuestionComment buffer: removeList){
            question.removeQuestionComment(buffer);
        }
        questionRepository.save(question);
    }

}
