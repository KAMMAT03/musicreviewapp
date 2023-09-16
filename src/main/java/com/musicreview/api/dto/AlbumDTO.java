package com.musicreview.api.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AlbumDTO {
    private String id;
    private String name;
    private List<ArtistDTO> artists;
    private LocalDate releaseDate;
    private String imageUrl;
    private List<TrackDTO> trackList;
}
