package com.musicreview.api.services.implementations;

import com.musicreview.api.dto.ArtistDTO;
import com.musicreview.api.services.ArtistService;
import com.musicreview.api.spotify.api.SpotifyApiHandler;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Override
    public ArtistDTO getArtistById(String id) {
        String searchContent = "artists/" + id;
        String response = SpotifyApiHandler.spotifyApiGetResponse(searchContent);

        return SpotifyApiHandler.extractArtistById(response);
    }
}
