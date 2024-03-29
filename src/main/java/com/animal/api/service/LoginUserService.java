package com.animal.api.service;

import com.animal.api.auth.LoginUser;
import com.animal.api.entity.User;
import com.animal.api.exception.UserNotFoundException;
import com.animal.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements UserDetailsService {


    private User user;
    private final UserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginUserService(UserRepository repository) {
        this.repository=repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public User getUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Can't find user."));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User loginUser = getUserById(username);
            return new LoginUser(loginUser);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("Username is wrong.");
        }
    }

}