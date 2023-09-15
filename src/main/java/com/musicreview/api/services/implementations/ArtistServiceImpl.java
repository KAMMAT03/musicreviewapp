package com.musicreview.api.services.implementations;

import com.musicreview.api.dto.ArtistDTO;
import com.musicreview.api.services.ArtistService;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Override
    public ArtistDTO getArtistById(String id, String token) {
        String searchContent = "artists/" + id;
        String response = SpotifyApiHandler.spotifyApiGetResponse(searchContent, token);

        return SpotifyApiHandler.extractArtistById(response, token);
    }
}
