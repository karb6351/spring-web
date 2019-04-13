package project.services;

import project.exceptions.VoteSameResponseException;
import project.models.User;
import project.models.Vote;

public interface VoteService {

    Vote vote(User user, Integer responseId) throws VoteSameResponseException;

    void cancelVote(Integer userId, Integer responseId);

    Vote getVoteByUserIdAndResponseId(Integer userId, Integer responseId);

}
