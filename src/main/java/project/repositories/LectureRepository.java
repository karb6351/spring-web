package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
}
