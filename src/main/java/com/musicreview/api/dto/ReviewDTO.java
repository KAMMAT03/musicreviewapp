package com.musicreview.api.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private long id;
    private long albumId;
    private long artistId;
    private String title;
    private String content;
    private int score;
    private int likes;
}
