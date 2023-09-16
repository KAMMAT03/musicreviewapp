package com.musicreview.api.services;

import com.musicreview.api.dto.ArtistDTO;

public interface ArtistService {
    ArtistDTO getArtistById(String id);
}
