package com.musicreview.api.exceptions;

import java.io.Serial;

public class TokenException extends RuntimeException{
    private static final long serialVersionUID = 3;
    public TokenException(){
        super("Couldn't access the SpotifyAPI");
    }
}
