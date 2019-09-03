package com.tradeshift.codingchallenge.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author milad
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) { super(message); }

    public NotFoundException(String s, Throwable t) {
        super(s, t);
    }

}
