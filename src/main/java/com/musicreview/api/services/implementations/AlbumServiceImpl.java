package com.musicreview.api.services.implementations;

import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.services.AlbumService;
import com.musicreview.api.spotify.api.SpotifyApiHandler;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Override
    public List<AlbumDTO> searchAlbum(String searchPhrase, String token) {
        String searchContent = "search?q=" + searchPhrase + "&type=album&market=PL&limit=10";
        String response = SpotifyApiHandler.spotifyApiGetResponse(searchContent, token);

        return SpotifyApiHandler.extractAlbums(response);
    }

    @Override
    public AlbumDTO getAlbumById(String id, String token) {
        String searchContent = "albums/" + id + "?market=PL";
        String response = SpotifyApiHandler.spotifyApiGetResponse(searchContent, token);

        return SpotifyApiHandler.extractAlbumDetailed(response);
    }


}
