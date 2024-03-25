package com.example.SongShareBackend.DTOs;

import lombok.Data;

@Data
public class SongDTO {
    private String title;
    private String band;
    private String category;
    private int likes;
}
