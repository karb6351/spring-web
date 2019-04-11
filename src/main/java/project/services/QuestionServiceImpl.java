package project.services;

import org.springframework.stereotype.Service;
import project.models.Question;
import project.repositories.QuestionRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements  QuestionService {

    @Resource
    QuestionRepository questionRepository;

    public List<Question> getQuestions(){
        return this.questionRepository.findAll();
    }

    public Question getQuestionById(Integer id){
        return this.questionRepository.findOne(id);
    }

    public int createQuestion(String question){
        Question q = this.questionRepository.save(new Question(question));
        return q.getId();
    }

    public void updateQuestion(Integer id, String question){
        Question q = this.getQuestionById(id);
        if (q != null){
            q.setQuestion(question);
            this.questionRepository.save(q);
        }
    }

    public void deleteQuestion(Integer id){
        Question q = this.getQuestionById(id);
        if (q != null){
            this.questionRepository.delete(q);
        }
    }


}
