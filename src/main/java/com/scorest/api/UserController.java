package com.scorest.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository service;

    public UserController(UserRepository service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

//    @GetMapping("/users/{id}")
//    public User retrieveUser(@PathVariable int id) {
//        return service.findUser(id).orElseThrow(() -> new NoUserFoundException("no user"));
//    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(loc).build();
    }

//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable int id) {
//        service.removeUser(id);
//    }
}
