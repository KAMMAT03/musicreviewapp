package com.musicreview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class TrackDTO {
    private String id;
    private String name;
    private List<ArtistDTO> artists;
}
