package com.musicreview.api.exceptions;

import java.io.Serial;

public class ReviewNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;
    public ReviewNotFoundException(){
        super("No review found for this album");
    }
    public ReviewNotFoundException(String message){
        super(message);
    }
}
