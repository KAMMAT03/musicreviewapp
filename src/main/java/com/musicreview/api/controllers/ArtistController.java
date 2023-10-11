package com.musicreview.api.controllers;


import com.musicreview.api.dto.ArtistDTO;
import com.musicreview.api.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/artist/")
public class ArtistController {
    private final ArtistService artistService;
    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable(value = "id") String id){
        return new ResponseEntity<>(artistService.getArtistById(id), HttpStatus.OK);
    }
}
