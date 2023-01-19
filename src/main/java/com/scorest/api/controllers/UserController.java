package com.scorest.api.controllers;

import com.scorest.api.entities.Post;
import com.scorest.api.entities.User;
import com.scorest.api.repository.PostRepository;
import com.scorest.api.repository.UserRepository;
import exceptions.NoUserFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository service;
    private final PostRepository postRepository;

    public UserController(UserRepository service, PostRepository postRepository) {
        this.service = service;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findById(id).orElseThrow(() -> new NoUserFoundException("no user"));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = service.save(user);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(loc).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getAllPostsByUserId(@PathVariable int id) {
        Optional<User> foundUser = service.findById(id);
        if (foundUser.isEmpty()) {
            throw new NoUserFoundException("user not found");
        }
        return foundUser.get().getPosts();
    }
    @PostMapping("/users/{id}/posts")
    public void createPostsByUserId(@PathVariable int id, @RequestBody Post post) {
        Optional<User> foundUser = service.findById(id);
        if (foundUser.isEmpty()) {
            throw new NoUserFoundException("user not found");
        }
        post.setUser(foundUser.get());
        postRepository.save(post);
    }
}
