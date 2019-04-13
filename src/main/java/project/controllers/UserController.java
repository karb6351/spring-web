package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import project.models.User;
import project.models.UserRole;
import project.services.UserRoleService;
import project.services.UserService;
import project.services.VoteService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    public class UserForm {

        private String username;
        private String[] roles;

        public UserForm(User user){
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


    @Autowired
    protected UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private VoteService voteService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "partial/user/index";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap modelMap){
        User user = userService.getUserById(id);
        if(user == null){
            return "redirect:/user/";
        }
        List<UserRole> roles = userRoleService.getUserRoles(user);
        modelMap.addAttribute("model", user);
        modelMap.addAttribute("roleList", roles);
        modelMap.addAttribute("userRoles", user.getUserRoles());
        return "partial/user/update";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public View update(@RequestBody MultiValueMap<String, String> formData, @PathVariable Integer id, ModelMap modelMap){
        System.out.println(formData.get("roles[]"));
        userService.updateUser(id, formData.getFirst("username"), formData.get("roles[]"));
        return new RedirectView("/user/", true);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public View delete(@PathVariable Integer id){
        voteService.deleteVolesByUserId(id);
        userService.deleteUser(id);
        return new RedirectView("/user/", true);
    }
}
