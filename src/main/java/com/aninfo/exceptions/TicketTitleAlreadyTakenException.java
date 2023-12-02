package com.aninfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TicketTitleAlreadyTakenException extends RuntimeException{
    public TicketTitleAlreadyTakenException(String message) {
        super(message);
    }
}
