package project.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.exceptions.DuplicateUserException;
import project.models.User;
import project.models.UserRole;
import project.repositories.UserRepository;
import project.repositories.UserRoleRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleRepository userRoleRepository ;

    @Override
    public String[] getRolesName(User user){
        List<String> bufferRoles = new ArrayList<>();
        for(UserRole userRole: user.getUserRoles()){
            bufferRoles.add(userRole.getRole());
        }
        String[] resultRoles = new String[bufferRoles.size()];
        return bufferRoles.toArray(resultRoles);
    }

    @Override
    @Transactional
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUserById(Integer id){
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public long register(String username, String password) throws DuplicateUserException{

        boolean isRegistered = this.isUserExist(username);
        if (isRegistered){
            throw new DuplicateUserException("Username already exist");
        }

        User resultUser = userRepository.save(new User(username, password));

        UserRole resultUserRole = userRoleRepository.save(new UserRole(resultUser, UserRole.ROLE_STUDENT));

        resultUser.addRole(resultUserRole);

        return resultUser.getId();
    }

    @Override
    @Transactional
    public void updateUser(Integer id, String username, List<String> roles) {
        User user = this.getUserById(id);
        user.setUsername(username);
        List<UserRole> newUserRoles = new ArrayList<>();
        for(String role: roles){
            newUserRoles.add(new UserRole(user, role));
        }
        user.setUserRoles(newUserRoles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user){
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        User resultUser = userRepository.findOne(id);
        if (resultUser != null){
            userRepository.delete(resultUser);
        }
    }

    @Override
    @Transactional
    public User getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        System.out.println(user.toString());
        return user;
    }

    @Override
    @Transactional
    public boolean isUserExist(String username){
        User user = userRepository.findByUsername(username);
        return !(user.getUsername() == null || user.getUsername().equals(""));
    }
}
