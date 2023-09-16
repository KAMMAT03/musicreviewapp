package com.musicreview.api.spotify.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.dto.ArtistDTO;
import com.musicreview.api.dto.TrackDTO;
import com.musicreview.api.exceptions.AlbumNotFoundException;
import lombok.Getter;
import lombok.Setter;

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
    @Getter
    @Setter
    private static String token;

    public static String spotifyApiGetResponse(String searchContent){
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
        JsonElement jsonElement = JsonParser.parseString(response);

        JsonObject albumsObject = Optional.ofNullable(jsonElement.getAsJsonObject().
                        getAsJsonObject("albums")).
                orElseThrow(AlbumNotFoundException::new);

        return getAlbums(albumsObject);
    }

    public static AlbumDTO extractAlbumDetailed(String response){
        JsonObject jsonObject = Optional.ofNullable(JsonParser.parseString(response).getAsJsonObject()).
                orElseThrow(AlbumNotFoundException::new);

        return getAlbumDto(jsonObject, true);
    }

    public static ArtistDTO extractArtistById(String response){
        JsonElement artistElement = Optional.ofNullable(JsonParser.parseString(response)).
                orElseThrow(AlbumNotFoundException::new);

        ArtistDTO artistDTO =  getArtistDTO(artistElement, true);

        String searchContent = "artists/" + artistDTO.getId() + "/albums?include_groups=album&market=PL";
        response = SpotifyApiHandler.spotifyApiGetResponse(searchContent);

        JsonObject albumsObject = Optional.ofNullable(JsonParser.parseString(response).getAsJsonObject()).
                orElseThrow(AlbumNotFoundException::new);

        artistDTO.setAlbums(getAlbums(albumsObject));

        return artistDTO;
    }

    private static List<AlbumDTO> getAlbums(JsonObject albumsObject){

        List<AlbumDTO> albumDTOList = new ArrayList<>();

        JsonArray itemsArray = Optional.ofNullable(albumsObject.getAsJsonArray("items")).
                orElseThrow(AlbumNotFoundException::new);

        for (JsonElement item : itemsArray){
            JsonObject itemObject = Optional.ofNullable(item.getAsJsonObject()).
                    orElseThrow(AlbumNotFoundException::new);

            albumDTOList.add(getAlbumDto(itemObject, false));
        }
        return albumDTOList;
    }

    private static AlbumDTO getAlbumDto(JsonObject jsonObject, boolean detailed){
        AlbumDTO albumDTO = new AlbumDTO();

        String name = jsonObject.get("name").getAsString();
        String id = jsonObject.get("id").getAsString();
        String imageUrl = getImageUrl(jsonObject);

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

    private static ArtistDTO getArtistDTO(JsonElement artist, boolean detailed) {
        JsonObject artistObject = Optional.ofNullable(artist.getAsJsonObject()).
                orElseThrow(AlbumNotFoundException::new);

        String artistId = artistObject.get("id").getAsString();
        String artistName = artistObject.get("name").getAsString();

        ArtistDTO artistDTO = new ArtistDTO();

        if (detailed) artistDTO.setImageUrl(getImageUrl(artistObject));

        artistDTO.setName(artistName);
        artistDTO.setId(artistId);
        return artistDTO;
    }

    private static List<ArtistDTO> getListArtistDTO(JsonObject jsonObject){
        JsonArray artistsArray = Optional.ofNullable(jsonObject.getAsJsonArray("artists")).
                orElseThrow(AlbumNotFoundException::new);

        List<ArtistDTO> artistDTOList = new ArrayList<>();

        for (JsonElement artist : artistsArray){
            ArtistDTO artistDTO = getArtistDTO(artist, false);

            artistDTOList.add(artistDTO);
        }

        return artistDTOList;
    }

    private static String getImageUrl(JsonObject jsonObject){
        JsonObject imageObject = Optional.ofNullable(jsonObject.
                        getAsJsonArray("images")).orElseThrow(AlbumNotFoundException::new).
                get(0).getAsJsonObject();

        return imageObject.get("url").getAsString();
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
