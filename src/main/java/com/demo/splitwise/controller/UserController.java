package com.demo.splitwise.controller;

import com.demo.splitwise.infrastructure.model.AppUser;
import com.demo.splitwise.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<AppUser> create(@RequestBody String name) {
        // TODO Spring validation framework to be used
        if (name == null || name.trim().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        AppUser appUser = new AppUser();
        appUser.setName(name);
        userRepository.save(appUser);

        return ResponseEntity.ok(appUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppUser> create(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<AppUser> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());

    }
}
