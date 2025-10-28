package com.netconf.exception;

public class NetconfException extends RuntimeException {
    public NetconfException(String message) {
        super(message);
    }

    public NetconfException(String message, Throwable cause) {
        super(message, cause);
    }
}