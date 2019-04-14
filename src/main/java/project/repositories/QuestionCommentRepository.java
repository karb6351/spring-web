package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.QuestionComment;

import java.util.List;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Integer> {

    List<QuestionComment> findAllByQuestionIdOrderByCreatedAtDesc(Integer id);
}
