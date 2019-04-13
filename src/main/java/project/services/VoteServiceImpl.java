package project.services;

import org.springframework.stereotype.Service;
import project.exceptions.VoteSameResponseException;
import project.models.Response;
import project.models.User;
import project.models.Vote;
import project.repositories.VoteRepository;

import javax.annotation.Resource;

@Service
public class VoteServiceImpl implements VoteService {

    @Resource
    VoteRepository voteRepository;

    @Resource
    ResponseService responseService;

    @Resource
    UserService userService;

    @Override
    public Vote vote(User user, Integer responseId) throws VoteSameResponseException {
        Vote oldVote = getVoteByUserIdAndResponseId(user.getId(), responseId);
        // vote same response, no need to vote again
        if (oldVote != null){
            throw new VoteSameResponseException("You are already vote this response");
        }
        Response response = responseService.getResourceById(responseId);
        Vote vote = new Vote(user,response);
        user.addVote(vote);
        response.addVote(vote);
        userService.updateUser(user);
        responseService.updateResponse(response);
        return voteRepository.save(vote);
    }

    @Override
    public void cancelVote(Integer userId, Integer responseId) {

    }

    @Override
    public Vote getVoteByUserIdAndResponseId(Integer userId, Integer responseId) {
        return voteRepository.findOneByUserIdAndResponseId(userId, responseId);
    }
}
