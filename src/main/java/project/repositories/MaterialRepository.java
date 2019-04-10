package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Lecture;
import project.models.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Material findByFilename(String filename);
}
