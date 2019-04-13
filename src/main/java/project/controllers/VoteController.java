package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.models.User;
import project.models.Vote;
import project.services.UserService;
import project.services.VoteService;

import java.security.Principal;

@Controller
public class VoteController {

    private static class ResponseParam {

        public Integer responseId;

    }

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;


    @RequestMapping(
            value = "/vote",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Vote> doVote(@RequestBody ResponseParam responseParam, Principal principal) {
        try{
            User user = userService.getUserByUsername(principal.getName());
            Vote vote = voteService.vote(user, responseParam.responseId);
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
