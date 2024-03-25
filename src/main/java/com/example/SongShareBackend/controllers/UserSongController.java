package com.example.SongShareBackend.controllers;

import com.example.SongShareBackend.DTOs.SongDTO;
import com.example.SongShareBackend.models.SongshareSong;
import com.example.SongShareBackend.services.UserSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usersongs")
public class UserSongController {

    private final Songcontroller songController;

    private final UserSongService userSongService;

    @Autowired
    public UserSongController(UserSongService userSongService, Songcontroller songcontroller) {
        this.userSongService = userSongService;
        this.songController = songcontroller;
    }

    @GetMapping("/{userId}/songs")
    public ResponseEntity<List<SongshareSong>> getAllSongsForUser(@PathVariable int userId) {
        List<SongshareSong> songs = userSongService.getAllSongsFromUser(userId);

        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(songs, HttpStatus.OK);
        }
    }

    @PostMapping("/{userId}/addsong")
    public ResponseEntity<List<SongshareSong>> addSongToUser(@PathVariable int userId, @RequestBody SongDTO songDTO) {

        SongshareSong newsong = songController.createSong(songDTO);

        // Add the new song to the user's list of songs
        userSongService.addUserToSong(userId, newsong);

        // Get the updated list of songs for the user and return it
        List<SongshareSong> userSongs = userSongService.getAllSongsFromUser(userId);

        return new ResponseEntity<>(userSongs, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/deletesong/{songId}")
    public ResponseEntity<List<SongshareSong>> deleteSongFromUser(@PathVariable int userId, @PathVariable Long songId) {
        // Delete the song from the user's list
        userSongService.deleteUserSong(songId);

        songController.deleteSong(songId);

        // Get the updated list of songs for the user and return it
        List<SongshareSong> userSongs = userSongService.getAllSongsFromUser(userId);

        return new ResponseEntity<>(userSongs, HttpStatus.OK);
    }
}
