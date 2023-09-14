package com.musicreview.api.services;

import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.dto.AlbumResponse;

import java.util.List;

public interface AlbumService {
    List<AlbumDTO> searchAlbum(String searchPhrase, String token);
    AlbumDTO getAlbumById(String id, String token);
}
