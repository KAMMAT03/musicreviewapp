package com.musicreview.api.dto;

import com.musicreview.api.models.UserEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewDTO {
    private long id;
    private String albumId;
    private String title;
    private String content;
    private int score;
    private int likes;
    private LocalDateTime dateOfPublication;
    private String username;
}
