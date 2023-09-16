package com.musicreview.api.spotify.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musicreview.api.exceptions.TokenException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class TokenTask {
    public void getTokenFromApi(){
        String token = getTokenFromResponse(spotifyApiPostRequest());
        SpotifyApiHandler.setToken(token);
    }

    private static String spotifyApiPostRequest(){
        String response;
        try {
            HttpURLConnection connection = getHttpURLConnection();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) throw new TokenException();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder responseSb = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseSb.append(inputLine);
                }

                response = responseSb.toString();
            }

            connection.disconnect();
        } catch (IOException e) {
            throw new TokenException();
        }

        return response;
    }

    private static HttpURLConnection getHttpURLConnection() throws IOException {
        String endpoint = "https://accounts.spotify.com/api/token";

        String clientId = "secretid";
        String clientSecret = "secretkey";

        URL url = new URL(endpoint);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String requestBody = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret;

        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
            outputStream.write(requestBodyBytes);
        }
        return connection;
    }

    private static String getTokenFromResponse(String response){
        JsonElement jsonElement = JsonParser.parseString(response);

        JsonObject jsonObject = Optional.ofNullable(jsonElement.getAsJsonObject()).
                orElseThrow(TokenException::new);

        return jsonObject.get("access_token").getAsString();
    }
}
