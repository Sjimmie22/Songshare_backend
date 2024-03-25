package com.example.SongShareBackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class SongshareUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "users")
    private List<SongshareSong> songs;
}
