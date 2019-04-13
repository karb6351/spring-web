package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Vote findOneByUserIdAndResponseId(Integer userId, Integer responseId);

    Integer countByResponseId(Integer responseId);
}
