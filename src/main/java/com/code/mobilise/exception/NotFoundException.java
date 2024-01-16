package com.code.mobilise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final String BOOK_NOT_FOUND = "BOOK NOT FOUND";

    public NotFoundException() {
        super(BOOK_NOT_FOUND);
    }

}
