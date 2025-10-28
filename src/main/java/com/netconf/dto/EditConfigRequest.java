package com.netconf.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class EditConfigRequest {
    @NotEmpty(message = "Session ID is required")
    private String sessionId;
    
    @NotEmpty(message = "Datastore is required")
    @Pattern(regexp = "running|candidate", message = "Invalid datastore")
    private String datastore;
    
    @NotEmpty(message = "Config XML is required")
    private String config;
    
    @Pattern(regexp = "merge|replace|none", message = "Invalid operation")
    private String defaultOperation = "merge";

    // Getters and Setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getDatastore() { return datastore; }
    public void setDatastore(String datastore) { this.datastore = datastore; }
    
    public String getConfig() { return config; }
    public void setConfig(String config) { this.config = config; }
    
    public String getDefaultOperation() { return defaultOperation; }
    public void setDefaultOperation(String defaultOperation) { this.defaultOperation = defaultOperation; }
}