package project.forms;

import project.models.User;
import project.services.UserService;

public class UserForm {

    private String username;
    private String[] roles;


    private UserService userService;

    public UserForm(UserService userService, User user){
        this.userService = userService;
        this.username = user.getUsername();
        this.roles = userService.getRolesName(user);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

}
