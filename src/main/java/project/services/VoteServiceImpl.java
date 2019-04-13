package project.services;

import org.springframework.stereotype.Service;
import project.exceptions.VoteSameResponseException;
import project.models.Response;
import project.models.User;
import project.models.Vote;
import project.repositories.VoteRepository;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Resource
    VoteRepository voteRepository;

    @Resource
    ResponseService responseService;

    @Resource
    UserService userService;

    @Override
    public Vote vote(User user, Integer responseId, Integer questionId) throws VoteSameResponseException {

        // vote same response, no need to vote again
        if (voteRepository.findOneByUserIdAndResponseId(user.getId(), responseId) != null){
            System.out.println("voted");
            throw new VoteSameResponseException("You are already vote this response");
        }

        //find oldVote
        Vote oldVote = null;
        for(Vote vote: user.getVotes()){
            if (vote.getResponse().getQuestionId().equals(questionId)){
                oldVote = vote;
                break;
            }
        }


        Response response = responseService.getResourceById(responseId);

        Vote vote;

        //update old vote when user is already voted
        if (oldVote != null){
//            Response oldResponse = oldVote.getResponse();
//            user.removeVote(oldVote);
//            oldResponse.removeVote(oldVote);
//            userService.updateUser(user);
//            responseService.updateResponse(oldResponse);
//            voteRepository.delete(oldVote);
            oldVote.setResponse(response);
            vote = oldVote;
        }else {

            vote = new Vote(user,response);
            user.addVote(vote);
            response.addVote(vote);
        }

//        userService.updateUser(user);
//        responseService.updateResponse(response);
        voteRepository.save(vote);
        return vote;
    }

    @Override
    public void cancelVote(Integer userId, Integer responseId) {

    }

    @Override
    public Vote getVoteByUserIdAndResponseId(Integer userId, Integer responseId) {
        return voteRepository.findOneByUserIdAndResponseId(userId, responseId);
    }

    @Override
    public void deleteVolesByUserId(Integer userId){
        List<Vote> votes = voteRepository.findAllByUserId(userId);
        for (Vote temp: votes){
            voteRepository.delete(temp);
        }
    }

    @Override
    public void deleteVolesByResponseId(Integer responseId){
        List<Vote> votes = voteRepository.findAllByResponseId(responseId);
        for (Vote temp: votes){
            voteRepository.delete(temp);
        }
    }

}
