package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.models.User;
import project.models.UserRole;
import project.repositories.UserRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticateService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    public static class Form {
        private long id;
        private String username;
        private String password;

        public Form(){}

        public Form(long id, String username, String password){
            this.id = id;
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString(){
            return this.id + " " +  this.username + " " +  this.password;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + s + "' not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : user.getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
