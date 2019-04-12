package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Response;

public interface ResponseRepository extends JpaRepository<Response, Integer> {

    long countByQuestionId(Integer questionId);
}
