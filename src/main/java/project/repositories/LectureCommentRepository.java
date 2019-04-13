package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.LectureComment;

import java.util.List;

@Repository
public interface LectureCommentRepository extends JpaRepository<LectureComment, Integer> {

    List<LectureComment> findAllByLectureIdOrderByCreatedAtDesc(Integer id);
}
