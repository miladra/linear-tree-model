package com.codingchallenge.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author milad
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String s, Throwable t) {
        super(s, t);
    }
}