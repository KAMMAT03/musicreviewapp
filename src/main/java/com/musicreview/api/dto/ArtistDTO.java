package com.musicreview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArtistDTO {
    private String id;
    private String name;
    private String imageUrl;
    private List<AlbumDTO> albums;
}
