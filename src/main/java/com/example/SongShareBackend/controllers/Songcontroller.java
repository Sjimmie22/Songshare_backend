package com.example.SongShareBackend.controllers;

import com.example.SongShareBackend.DTOs.SongDTO;
import com.example.SongShareBackend.Repositories.SongRepo;
import com.example.SongShareBackend.models.SongshareSong;
import com.example.SongShareBackend.services.Songservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class Songcontroller {
    private final Songservice songService;

    @Autowired
    public Songcontroller(Songservice songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<SongshareSong> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongshareSong> getSongById(@PathVariable Long id) {
        Optional<SongshareSong> song = songService.getSongById(id);

        if (song.isPresent()) {
            return ResponseEntity.ok(song.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public SongshareSong createSong(@RequestBody SongDTO songDTO) {
        SongshareSong newSong = new SongshareSong();
        newSong.setTitle(songDTO.getTitle());
        newSong.setBand(songDTO.getBand());
        newSong.setCategory(songDTO.getCategory());
        newSong.setLikes(songDTO.getLikes());

        SongshareSong answer = songService.createSong(newSong);

        return answer;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongshareSong> updateSong(@PathVariable Long id, @RequestBody SongDTO updatedSongDTO) {
        Optional<SongshareSong> optionalSong = songService.getSongById(id);

        if (optionalSong.isPresent()) {
            SongshareSong existingSong = optionalSong.get();

            // Update the song details
            existingSong.setTitle(updatedSongDTO.getTitle());
            existingSong.setBand(updatedSongDTO.getBand());
            existingSong.setCategory(updatedSongDTO.getCategory());

            // Save the updated song
            SongshareSong updatedSong = songService.updateSong(existingSong);

            return ResponseEntity.ok(updatedSong);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        try {
            // Attempt to delete the song
            if (songService.deleteSong(id)) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during deletion
            return ResponseEntity.status(500).build();
        }
    }
}
