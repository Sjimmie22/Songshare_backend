package com.example.SongShareBackend.Repositories;

import com.example.SongShareBackend.models.SongshareSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongRepo extends JpaRepository <SongshareSong, Integer> {
}
