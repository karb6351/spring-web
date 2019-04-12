package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import project.exceptions.FullResponseException;
import project.helpers.FlashSession;
import project.models.Question;
import project.models.Response;
import project.services.QuestionService;
import project.services.ResponseService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = "response/{questionId}/create", method = RequestMethod.GET)
    public String create(@PathVariable Integer questionId, ModelMap modelMap){
        modelMap.addAttribute("model", new Response());
        modelMap.addAttribute("questionId", questionId);
        return "partial/response/create_update";
    }


    @RequestMapping(value = "response/{questionId}/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public View save(@PathVariable Integer questionId, @RequestBody MultiValueMap<String, String> formData, ModelMap modelMap) {
        String response = formData.getFirst("response");
        try {
            int id = responseService.createResponse(response, questionId);
            return new RedirectView("/question/" + questionId, true);
        }catch (FullResponseException fre){
            modelMap = FlashSession.addErrorMessage(modelMap, fre.getMessage());
            return new RedirectView("/question/" + questionId, true);
        }
    }

    @RequestMapping(value = "response/{questionId}/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer questionId,@PathVariable Integer id, ModelMap modelMap){
        Response response = responseService.getResourceById(id);
        modelMap.addAttribute("model", response);
        modelMap.addAttribute("questionId", questionId);
        return "partial/response/create_update";
    }


    @RequestMapping(value = "/response/{questionId}/edit/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public View update(@PathVariable Integer questionId, @PathVariable Integer id, @RequestBody MultiValueMap<String, String> formData){
        responseService.updateResponse(id, formData.getFirst("response"));
        return new RedirectView("/question/" + questionId, true);
    }

    @RequestMapping(value = "/response/delete/{id}", method = RequestMethod.POST)
    public View delete(@PathVariable Integer id){
        Response response = responseService.getResourceById(id);
        Question question = questionService.deleteResponse(response);
        return new RedirectView("/question/" + question.getId(), true);
    }


    @RequestMapping(value = "/response/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, HttpServletRequest request) {
        String response = request.getParameter("response");
        if (id == null) {
            return new ResponseEntity<>("{ \"status\" : false }", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{ \"status\" : true, \"id\": " + id + " }", HttpStatus.OK);
    }

}
