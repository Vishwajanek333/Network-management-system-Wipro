package com.netconf.service;

import com.netconf.model.NetconfSession;
import java.util.List;

public interface NetconfService {
    // Connection management
    NetconfSession connect(String host, int port, String username, String password);
    void disconnect(String sessionId);
    
    // Core NETCONF operations
    String getConfig(String sessionId, String datastore, String filter);
    boolean editConfig(String sessionId, String datastore, String config, String defaultOperation);
    boolean copyConfig(String sessionId, String source, String target);
    boolean deleteConfig(String sessionId, String datastore);
    boolean lock(String sessionId, String datastore);
    boolean unlock(String sessionId, String datastore);
    boolean commit(String sessionId);
    boolean validate(String sessionId, String datastore);
    
    // Capability operations
    List<String> getCapabilities(String sessionId);
    
    // Session management
    List<NetconfSession> getActiveSessions();
    NetconfSession getSession(String sessionId);
}