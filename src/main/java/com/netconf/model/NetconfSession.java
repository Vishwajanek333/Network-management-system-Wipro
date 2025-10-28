package com.netconf.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NetconfSession {
    private final String sessionId;
    private final String host;
    private final int port;
    private final String username;
    private final LocalDateTime created;
    private LocalDateTime lastActivity;
    private List<String> capabilities;
    private String connectionStatus;

    public NetconfSession(String sessionId, String host, int port, String username) {
        this.sessionId = sessionId;
        this.host = host;
        this.port = port;
        this.username = username;
        this.created = LocalDateTime.now();
        this.lastActivity = LocalDateTime.now();
        this.capabilities = new ArrayList<>();
        this.connectionStatus = "CONNECTED";
    }

    public void updateActivity() {
        this.lastActivity = LocalDateTime.now();
    }

    // Getters
    public String getSessionId() { return sessionId; }
    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getUsername() { return username; }
    public LocalDateTime getCreated() { return created; }
    public LocalDateTime getLastActivity() { return lastActivity; }
    public List<String> getCapabilities() { return capabilities; }
    public String getConnectionStatus() { return connectionStatus; }
    
    // Setters
    public void setCapabilities(List<String> capabilities) { this.capabilities = capabilities; }
    public void setConnectionStatus(String connectionStatus) { this.connectionStatus = connectionStatus; }
}