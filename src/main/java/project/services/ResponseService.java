package project.services;

import project.exceptions.FullResponseException;
import project.models.Response;

public interface ResponseService {

    Response getResourceById(int id);

    int createResponse(String response, Integer questionId)  throws FullResponseException;

    void updateResponse(Integer id, String response);

    void deleteResponse(Integer id);

    boolean isFullResponse(Integer questionId);

}
