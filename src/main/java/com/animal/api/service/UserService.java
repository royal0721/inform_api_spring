package com.animal.api.service;

import com.animal.api.entity.User;
import com.animal.api.exception.UserNotFoundException;
import com.animal.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository) {

        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User newUser(User newUser) {

        Optional<User> UserOptional = repository.findById(newUser.getId());

        if (UserOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
        } else {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return repository.save(newUser);
        }

    }

    public User getOne(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }

    public User updateUser(User newUser, String id) {

        return repository.findById(id)
                .map(user -> {
                    user.setFirst_name(newUser.getFirst_name());
                    user.setLast_name(newUser.getLast_name());
                    user.setEmail(newUser.getEmail());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}
