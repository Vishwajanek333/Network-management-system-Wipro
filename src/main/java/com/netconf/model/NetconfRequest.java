package com.netconf.model;

import java.time.LocalDateTime;
import java.util.Map;

public class NetconfRequest {
    private final String sessionId;
    private final String operation;
    private final Map<String, String> parameters;
    private final LocalDateTime timestamp;

    public NetconfRequest(String sessionId, String operation, Map<String, String> parameters) {
        this.sessionId = sessionId;
        this.operation = operation;
        this.parameters = parameters;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public String getSessionId() { return sessionId; }
    public String getOperation() { return operation; }
    public Map<String, String> getParameters() { return parameters; }
    public LocalDateTime getTimestamp() { return timestamp; }
}