package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Vote;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Vote findOneByUserIdAndResponseId(Integer userId, Integer responseId);

    Integer countByResponseId(Integer responseId);

    void deleteByUserId(Integer userId);

    void deleteByResponseId(Integer responseId);

    List<Vote> findAllByResponseId(Integer responseId);

    List<Vote> findAllByUserId(Integer userId);
}
