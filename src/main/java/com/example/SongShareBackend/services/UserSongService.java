package com.example.SongShareBackend.services;

import com.example.SongShareBackend.Repositories.SongRepo;
import com.example.SongShareBackend.Repositories.UserRepo;
import com.example.SongShareBackend.models.SongshareSong;
import com.example.SongShareBackend.models.SongshareUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserSongService {

    private final UserRepo userRepository;
    private final SongRepo songRepository;

    @Autowired
    public UserSongService(UserRepo userRepository, SongRepo songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public List<SongshareSong> getAllSongsFromUser(int userid) {
        Optional<SongshareUser> userOptional = userRepository.findById(userid);

        if (userOptional.isPresent()) {
            SongshareUser user = userOptional.get();
            return user.getSongs();
        } else {
            return Collections.emptyList();
        }
    }

    public void addUserToSong(int userId, SongshareSong song) {
        Optional<SongshareUser> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            SongshareUser user = userOptional.get();

            List<SongshareUser> SongUsers = song.getUsers();

            // Initialize the SongUsers list if it's null
            if (SongUsers == null) {
                SongUsers = new ArrayList<>();
            }

            SongUsers.add(user);

            song.setUsers(SongUsers); // Set the updated list of users in the song

            songRepository.save(song);
        } else {
            // Handle case when the user with the given ID is not found
            System.err.println("User not found for id: " + userId);
            //throw new SongshareUserNotFoundException("User not found for id: " + userId);
        }
    }


    public void deleteUserSong(Long songId) {
        Optional<SongshareSong> SongOptional = songRepository.findById(songId.intValue());

        if (SongOptional.isPresent()){
            SongshareSong song = SongOptional.get();
            song.setUsers(new ArrayList<>());
            songRepository.save(song);
        }
        else {
            // Handle case when the user with the given ID is not found
            System.err.println("Song not found for id: " + songId);
            //throw new SongshareUserNotFoundException("User not found for id: " + userId);
        }

    }
}
