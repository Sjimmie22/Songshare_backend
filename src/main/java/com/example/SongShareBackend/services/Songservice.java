package com.example.SongShareBackend.services;

import com.example.SongShareBackend.Repositories.SongRepo;
import com.example.SongShareBackend.Repositories.UserRepo;
import com.example.SongShareBackend.models.SongshareSong;
import com.example.SongShareBackend.models.SongshareUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Songservice {
    private final SongRepo songRepository;

    @Autowired
    public Songservice(SongRepo songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongshareSong> getAllSongs() {
        return songRepository.findAll();
    }

    public Optional<SongshareSong> getSongById(Long id) {
        return songRepository.findById(Math.toIntExact(id));
    }

    public SongshareSong createSong(SongshareSong song) {
        song = songRepository.save(song);
        return song;
    }

    public SongshareSong updateSong(SongshareSong song) {
        return songRepository.save(song);
    }

    public boolean deleteSong(Long id) {
        Optional<SongshareSong> optionalSong = songRepository.findById(Math.toIntExact(id));

        if (optionalSong.isPresent()) {
            songRepository.deleteById(Math.toIntExact(id));
            return true; // Deletion successful
        } else {
            return false; // Song not found
        }
    }

}
