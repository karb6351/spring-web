package project.services;

import project.exceptions.DuplicateUserException;
import project.models.User;

import java.util.List;

public interface UserService {

    long register(String username, String password) throws DuplicateUserException;

    List<User> getUsers();

    User getUserById(Integer id);

    String[] getRolesName(User user);

    void updateUser(Integer id, String username, List<String> roles);

    void deleteUser(Integer id);

    User getUserByUsername(String username);

    boolean isUserExist(String username);
}
