package com.musicreview.api.services;

import com.musicreview.api.dto.AlbumDTO;

import java.util.List;

public interface AlbumService {
    List<AlbumDTO> searchAlbum(String searchPhrase);
    AlbumDTO getAlbumById(String id);
}
