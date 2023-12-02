package com.aninfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FAILED_DEPENDENCY)
public class CouldNotAccessAPI extends RuntimeException {

    public CouldNotAccessAPI(String message) {
        super(message);
    }
}