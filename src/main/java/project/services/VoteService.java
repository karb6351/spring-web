package project.services;

import project.exceptions.VoteSameResponseException;
import project.models.Response;
import project.models.User;
import project.models.Vote;

import java.util.List;

public interface VoteService {

    Vote vote(User user, Integer responseId, Integer questionId) throws VoteSameResponseException;

    void cancelVote(Integer userId, Integer responseId);

    Vote getVoteByUserIdAndResponseId(Integer userId, Integer responseId);

    void deleteVolesByUserId(Integer userId);

    void deleteVolesByResponseId(Integer responseId);


}
