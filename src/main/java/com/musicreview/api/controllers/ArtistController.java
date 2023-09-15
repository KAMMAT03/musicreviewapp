package com.musicreview.api.controllers;


import com.musicreview.api.dto.ArtistDTO;
import com.musicreview.api.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ArtistController {
    private ArtistService artistService;
    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("get-artist/{token}/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable(value = "token") String token, @PathVariable(value = "id") String id){
        return new ResponseEntity<>(artistService.getArtistById(id, token), HttpStatus.OK);
    }
}
