package com.tradeshift.codingchallenge.common.exception;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
    }

    public NotFoundException(String s) {
        super(s);
    }
}
