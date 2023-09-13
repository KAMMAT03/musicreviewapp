package com.musicreview.api.dto;

import lombok.Data;

import java.net.URL;
import java.util.List;

@Data
public class ArtistDTO {
    private long id;
    private String name;
    private List<AlbumDTO> albums;
    private String imageUrl;
}
