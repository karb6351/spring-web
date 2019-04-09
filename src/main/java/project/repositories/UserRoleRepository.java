package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
