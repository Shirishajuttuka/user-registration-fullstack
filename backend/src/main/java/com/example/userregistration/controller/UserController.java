package com.example.userregistration.controller;

import com.example.userregistration.model.User;
import com.example.userregistration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")   // Vue frontend
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return service.createUser(user);
    }

    // READ
    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteUser(id);
    }
}
