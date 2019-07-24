package com.tradeshift.codingchallenge.exception;

/**
 * @author milad
 */
public class BadRequestException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String s, Throwable t) {
        super(s, t);
    }
}