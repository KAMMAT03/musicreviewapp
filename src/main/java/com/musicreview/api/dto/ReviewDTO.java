package com.musicreview.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {
    private long id;
    private String albumId;
    private String title;
    private String content;
    private int score;
    private int likes;
    private LocalDateTime dateOfPublication;
}
