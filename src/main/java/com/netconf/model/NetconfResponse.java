package com.netconf.model;

import java.time.LocalDateTime;

public class NetconfResponse {
    private final boolean success;
    private final String message;
    private final Object data;
    private final LocalDateTime timestamp;

    public NetconfResponse(boolean success, String message) {
        this(success, message, null);
    }

    public NetconfResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Object getData() { return data; }
    public LocalDateTime getTimestamp() { return timestamp; }
}