package com.musicreview.api.dto;

import lombok.Data;

import java.net.URL;
import java.util.Date;
import java.util.List;

@Data
public class AlbumDTO {
    private String id;
    private List<String> trackList;
    private String imageUrl;
    private String artistName;
    private String name;
    private long artistId;
    private Date releaseDate;
    private List<ReviewDTO> reviews;
}
