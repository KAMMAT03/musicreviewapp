package com.musicreview.api.exceptions;

import java.io.Serial;

public class CustomAuthorisationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 4;

    public CustomAuthorisationException(String message) {
        super(message);
    }
}
