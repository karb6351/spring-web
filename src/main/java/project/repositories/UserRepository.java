package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
