package com.example.SongShareBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class SongshareSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Band")
    private String Band;

    @Column(name = "Category")
    private String Category;

    @Column(name = "Likes")
    private int Likes;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "songshare_User_Song",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<SongshareUser> users;
}
