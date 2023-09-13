package com.musicreview.api.services.implementations;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.dto.AlbumResponse;
import com.musicreview.api.exceptions.AlbumNotFoundException;
import com.musicreview.api.services.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Override
    public List<AlbumDTO> searchAlbum(String searchContent, String token) {
        String response = getRequest(searchContent, token);

        return extractAlbums(response);
    }

    @Override
    public AlbumDTO getAlbumById(int id) {
        return null;
    }

    private String getRequest(String searchContent, String token){
        String responseBody = "";
        try {
            String apiUrl = "https://api.spotify.com/v1/search?q=" + searchContent + "&type=album&market=PL&limit=5";

            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

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
            e.printStackTrace();
        }
        return responseBody;
    }

    private List<AlbumDTO> extractAlbums(String response){
        List<AlbumDTO> albumDTOList = new ArrayList<>();

        JsonElement jsonElement = JsonParser.parseString(response);

        JsonObject albumsObject = Optional.ofNullable(jsonElement.getAsJsonObject().
                getAsJsonObject("albums")).
                    orElseThrow(() -> new AlbumNotFoundException("Could not find any album matching the given phrase"));

        JsonArray itemsArray = Optional.ofNullable(albumsObject.getAsJsonArray("items")).
                orElseThrow(() -> new AlbumNotFoundException("Could not find any album matching the given phrase"));

        for (JsonElement item : itemsArray){
            AlbumDTO albumDTO = new AlbumDTO();

            JsonObject albumObject = Optional.ofNullable(item.getAsJsonObject()).
                    orElseThrow(() -> new AlbumNotFoundException("Could not find any album matching the given phrase"));

            String albumId = albumObject.get("id").getAsString();
            String albumName = albumObject.get("name").getAsString();

            JsonObject imageObject = Optional.ofNullable(albumObject.getAsJsonArray("images")).
                    orElseThrow(() -> new AlbumNotFoundException("Could not find any album matching the given phrase")).get(0).getAsJsonObject();
            String imageUrl = imageObject.get("url").getAsString();

            albumDTO.setId(albumId);
            albumDTO.setImageUrl(imageUrl);
            albumDTO.setName(albumName);
            albumDTOList.add(albumDTO);
        }
        return albumDTOList;
    }
}
