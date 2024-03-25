package com.example.SongShareBackend.Repositories;

import com.example.SongShareBackend.models.SongshareUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository <SongshareUser, Integer>  {
    Optional<SongshareUser> findByUsername(String username);
}
