package com.musicreview.api.exceptions;

public class CustomAuthorisationException extends RuntimeException{
    private static final long serialVersionUID = 4;

    public CustomAuthorisationException(String message) {
        super(message);
    }
}
