package com.musicreview.api.exceptions;

public class AlbumNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 2;
    public AlbumNotFoundException(){
        super("Could not find any album matching the given phrase");
    }
    public AlbumNotFoundException(String message){
        super(message);
    }
}
