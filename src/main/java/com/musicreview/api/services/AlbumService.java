package com.musicreview.api.services;

import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.responses.AlbumResponse;

public interface AlbumService {
    AlbumResponse searchAlbum(String searchPhrase, int pageNo, int pageSize);
    AlbumDTO getAlbumById(String id);
}
