package com.musicreview.api.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
