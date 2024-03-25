package com.example.SongShareBackend.services;

import com.example.SongShareBackend.models.SongshareUser;
import com.example.SongShareBackend.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Userservice {
    private final UserRepo userRepository;

    @Autowired
    public Userservice(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public SongshareUser getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public SongshareUser getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<SongshareUser> getAllUsers() {
        return userRepository.findAll();
    }
}
