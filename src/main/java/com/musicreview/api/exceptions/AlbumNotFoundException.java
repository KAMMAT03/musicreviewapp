package com.musicreview.api.exceptions;

import java.io.Serial;

public class AlbumNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 2;
    public AlbumNotFoundException(){
        super("Could not find any album matching the given phrase");
    }
    public AlbumNotFoundException(String message){
        super(message);
    }
}
