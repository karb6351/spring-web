package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import project.exceptions.VoteSameResponseException;
import project.models.Question;
import project.models.User;
import project.models.Vote;
import project.services.QuestionService;
import project.services.UserService;
import project.services.VoteService;

import java.security.Principal;

@Controller
public class VoteController {

    private static class ResponseParam {
        public Integer responseId;
        public Integer questionId;
    }

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;



    @RequestMapping(
            value = "/vote",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity doVote(@RequestBody ResponseParam responseParam, Principal principal) {
        try{
            User user = userService.getUserByUsername(principal.getName());
            Vote vote = voteService.vote(user, responseParam.responseId, responseParam.questionId);
            return new ResponseEntity<>("{}", HttpStatus.OK);
        }catch (VoteSameResponseException vsre){
            return new ResponseEntity<>("{'message' : " +  vsre.getMessage() + "}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/vote/result/{questionId}", method = RequestMethod.GET)
    public String result(@PathVariable Integer questionId, ModelMap modelMap){
        Question question = questionService.getQuestionById(questionId);
        String[] responseLabel = question.getResponseName();

        modelMap.addAttribute("question", question);
        return "partial/vote/result";
    }


}
