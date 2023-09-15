package com.musicreview.api.services.implementations;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.dto.ArtistDTO;
import com.musicreview.api.dto.TrackDTO;
import com.musicreview.api.exceptions.AlbumNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpotifyApiHandler {

    public static String spotifyApiGetRequest(String searchContent, String token){
        String responseBody;
        try {
            String apiUrl = "https://api.spotify.com/v1/" + searchContent;

            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Authorization", "Bearer " + token);

            int code = connection.getResponseCode();
            if (code != 200) throw new AlbumNotFoundException();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            responseBody = response.toString();

            connection.disconnect();
        } catch (IOException e) {
            throw new AlbumNotFoundException();
        }
        return responseBody;
    }

    public static List<AlbumDTO> extractAlbums(String response){
        List<AlbumDTO> albumDTOList = new ArrayList<>();

        JsonElement jsonElement = JsonParser.parseString(response);

        JsonObject albumsObject = Optional.ofNullable(jsonElement.getAsJsonObject().
                        getAsJsonObject("albums")).
                orElseThrow(AlbumNotFoundException::new);

        JsonArray itemsArray = Optional.ofNullable(albumsObject.getAsJsonArray("items")).
                orElseThrow(AlbumNotFoundException::new);

        for (JsonElement item : itemsArray){
            albumDTOList.add(extractAlbumDto(item.getAsJsonObject(), false));
        }
        return albumDTOList;
    }

    public static AlbumDTO extractAlbumDetailed(String response){
        JsonObject jsonObject = Optional.ofNullable(JsonParser.parseString(response).getAsJsonObject()).
                orElseThrow(AlbumNotFoundException::new);

        return extractAlbumDto(jsonObject, true);
    }

    public static AlbumDTO extractAlbumDto(JsonObject jsonObject, boolean detailed){
        AlbumDTO albumDTO = new AlbumDTO();

        String name = jsonObject.get("name").getAsString();
        String id = jsonObject.get("id").getAsString();

        JsonObject imageObject = Optional.ofNullable(jsonObject.
                        getAsJsonArray("images")).orElseThrow(AlbumNotFoundException::new).
                get(0).getAsJsonObject();

        String imageUrl = imageObject.get("url").getAsString();

        List<ArtistDTO> artistDTOList = getListArtistDTO(jsonObject);

        if (detailed){
            List<TrackDTO> trackList = getTrackList(jsonObject);

            String releaseDateString = jsonObject.get("release_date").getAsString();
            LocalDate releaseDate = LocalDate.parse(releaseDateString);

            albumDTO.setReleaseDate(releaseDate);
            albumDTO.setTrackList(trackList);
        }

        albumDTO.setArtists(artistDTOList);
        albumDTO.setId(id);
        albumDTO.setImageUrl(imageUrl);
        albumDTO.setName(name);

        return albumDTO;
    }

    public static ArtistDTO getArtistDTO(JsonElement artist) {
        JsonObject artistObject = Optional.ofNullable(artist.getAsJsonObject()).
                orElseThrow(AlbumNotFoundException::new);

        String artistId = artistObject.get("id").getAsString();
        String artistName = artistObject.get("name").getAsString();

        ArtistDTO artistDTO = new ArtistDTO();

        artistDTO.setName(artistName);
        artistDTO.setId(artistId);
        return artistDTO;
    }

    private static List<ArtistDTO> getListArtistDTO(JsonObject jsonObject){
        JsonArray artistsArray = Optional.ofNullable(jsonObject.getAsJsonArray("artists")).
                orElseThrow(AlbumNotFoundException::new);

        List<ArtistDTO> artistDTOList = new ArrayList<>();

        for (JsonElement artist : artistsArray){
            ArtistDTO artistDTO = getArtistDTO(artist);

            artistDTOList.add(artistDTO);
        }

        return artistDTOList;
    }

    private static List<TrackDTO> getTrackList(JsonObject jsonObject){
        JsonObject tracksObject = Optional.ofNullable(jsonObject.getAsJsonObject("tracks")).
                orElseThrow(AlbumNotFoundException::new);

        JsonArray itemsArray = Optional.ofNullable(tracksObject.getAsJsonArray("items")).
                orElseThrow(AlbumNotFoundException::new);

        List<TrackDTO> trackList = new ArrayList<>();

        for (JsonElement track : itemsArray){
            JsonObject trackObject = Optional.ofNullable(track.getAsJsonObject()).
                    orElseThrow(AlbumNotFoundException::new);

            TrackDTO trackDTO = new TrackDTO();

            List<ArtistDTO> artistDTOList = getListArtistDTO(trackObject);

            String name = trackObject.get("name").getAsString();

            String id = trackObject.get("id").getAsString();

            trackDTO.setArtists(artistDTOList);
            trackDTO.setName(name);
            trackDTO.setId(id);

            trackList.add(trackDTO);
        }

        return trackList;
    }
}
