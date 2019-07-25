package com.tradeshift.codingchallenge.common.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String s, Throwable t) {
        super(s, t);
    }
}
