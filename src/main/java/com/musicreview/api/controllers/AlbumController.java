package com.musicreview.api.controllers;

import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AlbumController {
    private AlbumService albumService;
    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("search-album/{token}/{content}")
    public ResponseEntity<List<AlbumDTO>> searchForAlbum(@PathVariable(value = "token") String token, @PathVariable(value = "content") String content){
        return new ResponseEntity<>(albumService.searchAlbum(content, token), HttpStatus.OK);
    }

    @GetMapping("get-album/{token}/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable(value = "token") String token, @PathVariable(value = "id") String id){
        return new ResponseEntity<>(albumService.getAlbumById(id, token), HttpStatus.OK);
    }
}
