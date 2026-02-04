package com.example.userregistration.service;

import com.example.userregistration.model.User;
import com.example.userregistration.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public User createUser(User user) {
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return repo.save(user);
    }

    // READ
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // UPDATE
    public User updateUser(String id, User user) {
        User existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setFullName(user.getFullName());
        existing.setEmail(user.getEmail());
        existing.setMobile(user.getMobile());
        existing.setPassword(user.getPassword());
        existing.setDob(user.getDob());

        return repo.save(existing);
    }

    // DELETE
    public void deleteUser(String id) {
        repo.deleteById(id);
    }
}
