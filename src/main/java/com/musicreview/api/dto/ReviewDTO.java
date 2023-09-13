package com.musicreview.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private long id;
    private long albumId;
    private long artistId;
    private String title;
    private String content;
    private int score;
    private int likes;
    private Date dateOfPublication;
}
