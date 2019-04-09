package project.services;

import org.springframework.stereotype.Service;
import project.models.User;
import project.models.UserRole;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Override
    public List<UserRole> getUserRoles(User user) {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(user, UserRole.ROLE_STUDENT));
        userRoles.add(new UserRole(user, UserRole.ROLE_LECTURER));
        return userRoles;
    }
}
