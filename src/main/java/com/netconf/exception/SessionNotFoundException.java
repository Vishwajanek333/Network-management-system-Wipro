package com.netconf.exception;

public class SessionNotFoundException extends NetconfException {
    public SessionNotFoundException(String message) {
        super(message);
    }
}