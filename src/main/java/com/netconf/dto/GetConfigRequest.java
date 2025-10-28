package com.netconf.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class GetConfigRequest {
    @NotEmpty(message = "Session ID is required")
    private String sessionId;
    
    @NotEmpty(message = "Datastore is required")
    @Pattern(regexp = "running|startup|candidate", message = "Invalid datastore")
    private String datastore;
    
    private String filter;

    // Getters and Setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getDatastore() { return datastore; }
    public void setDatastore(String datastore) { this.datastore = datastore; }
    
    public String getFilter() { return filter; }
    public void setFilter(String filter) { this.filter = filter; }
}