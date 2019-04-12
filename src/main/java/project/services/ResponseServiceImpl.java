package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.exceptions.FullResponseException;
import project.models.Question;
import project.models.Response;
import project.repositories.ResponseRepository;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private QuestionService questionService;

    @Override
    @Transactional
    public Response getResourceById(int id) {
        return responseRepository.findOne(id);
    }

    @Override
    @Transactional
    public int createResponse(String response, Integer questionId) throws FullResponseException {
        if (isFullResponse(questionId)){
            throw new FullResponseException();
        }
        Question question = questionService.getQuestionById(questionId);
        Response r = new Response(response, question);
        r.setQuestionId(questionId);
        return responseRepository.save(r).getId();
    }

    @Override
    @Transactional
    public void updateResponse(Integer id, String response) {
        Response r = getResourceById(id);
        if (r != null){
            r.setResponse(response);
            responseRepository.save(r);
        }
    }

    @Override
    @Transactional
    public void deleteResponse(Integer id) {
        Response r = getResourceById(id);
        if (r != null){
            responseRepository.delete(r);
        }
    }

    @Override
    @Transactional
    public boolean isFullResponse(Integer questionId){
        return responseRepository.countByQuestionId(questionId) >= Question.LIMITED_RESPONSE;
    }

}
