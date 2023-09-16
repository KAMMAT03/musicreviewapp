package com.musicreview.api.controllers;

import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums/")
public class AlbumController {
    private AlbumService albumService;
    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("search")
    public ResponseEntity<List<AlbumDTO>> searchForAlbum(@RequestParam(value = "token", defaultValue = "") String token,
                                                         @RequestParam(value = "content", defaultValue = "") String content){
        return new ResponseEntity<>(albumService.searchAlbum(content, token), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@RequestParam(value = "token", defaultValue = "") String token,
                                                 @PathVariable(value = "id") String id){
        return new ResponseEntity<>(albumService.getAlbumById(id, token), HttpStatus.OK);
    }
}
