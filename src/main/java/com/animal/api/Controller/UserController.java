package com.animal.api.Controller;

import com.animal.api.Entity.User;
import com.animal.api.Exception.UserNotFoundException;
import com.animal.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository repository;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(UserRepository repository) {

        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    // register function (store encoded password)
    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        // if user id exists, it will return status 400.
        Optional<User> UserOptional = repository.findById(newUser.getId());

        if (UserOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
        } else {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return repository.save(newUser);
        }
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }

    // 待確定什麼資料需要修改
    @PutMapping("/users/{id}")
    User replaceInform(@RequestBody User newUser, @PathVariable String id) {

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

    @DeleteMapping("/users/{id}")
    void deleteInform(@PathVariable String id) {
        repository.deleteById(id);
    }
}
