package project.services;

import project.models.User;
import project.models.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getUserRoles(User s);
}
