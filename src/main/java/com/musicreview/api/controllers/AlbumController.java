package com.musicreview.api.controllers;

import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.responses.AlbumResponse;
import com.musicreview.api.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums/")
public class AlbumController {
    private AlbumService albumService;
    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("search")
    public ResponseEntity<AlbumResponse> searchForAlbum(@RequestParam(value = "content", defaultValue = "") String content,
                                                        @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(albumService.searchAlbum(content, pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable(value = "id") String id){
        return new ResponseEntity<>(albumService.getAlbumById(id), HttpStatus.OK);
    }
}
