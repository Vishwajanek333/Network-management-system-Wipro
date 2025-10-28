package com.netconf.service;

import com.netconf.exception.SessionNotFoundException;
import com.netconf.model.NetconfSession;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NetconfServiceImpl implements NetconfService {
    private final Map<String, NetconfSession> sessions = new ConcurrentHashMap<>();

    @Override
    public NetconfSession connect(String host, int port, String username, String password) {
        String sessionId = "session-" + UUID.randomUUID().toString();
        NetconfSession session = new NetconfSession(sessionId, host, port, username);
        
        // Add mock capabilities
        List<String> capabilities = Arrays.asList(
            "urn:ietf:params:netconf:base:1.0",
            "urn:ietf:params:netconf:base:1.1",
            "urn:ietf:params:netconf:capability:writable-running:1.0",
            "urn:ietf:params:netconf:capability:candidate:1.0",
            "urn:ietf:params:netconf:capability:validate:1.1"
        );
        session.setCapabilities(capabilities);
        
        sessions.put(sessionId, session);
        return session;
    }

    @Override
    public void disconnect(String sessionId) {
        NetconfSession session = getSession(sessionId);
        session.setConnectionStatus("DISCONNECTED");
        sessions.remove(sessionId);
    }

    @Override
    public String getConfig(String sessionId, String datastore, String filter) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        
        // Mock configuration response
        return "<data>\n" +
               "  <interfaces>\n" +
               "    <interface>\n" +
               "      <name>eth0</name>\n" +
               "      <enabled>true</enabled>\n" +
               "      <ip-address>192.168.1.100</ip-address>\n" +
               "    </interface>\n" +
               "  </interfaces>\n" +
               "</data>";
    }

    @Override
    public boolean editConfig(String sessionId, String datastore, String config, String defaultOperation) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        return true;
    }

    @Override
    public boolean copyConfig(String sessionId, String source, String target) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        return true;
    }

    @Override
    public boolean deleteConfig(String sessionId, String datastore) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        return true;
    }

    @Override
    public boolean lock(String sessionId, String datastore) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        return true;
    }

    @Override
    public boolean unlock(String sessionId, String datastore) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        return true;
    }

    @Override
    public boolean commit(String sessionId) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        return true;
    }

    @Override
    public boolean validate(String sessionId, String datastore) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        return true;
    }

    @Override
    public List<String> getCapabilities(String sessionId) {
        NetconfSession session = getSession(sessionId);
        session.updateActivity();
        return session.getCapabilities();
    }

    @Override
    public List<NetconfSession> getActiveSessions() {
        return new ArrayList<>(sessions.values());
    }

    @Override
    public NetconfSession getSession(String sessionId) {
        NetconfSession session = sessions.get(sessionId);
        if (session == null) {
            throw new SessionNotFoundException("Session not found: " + sessionId);
        }
        return session;
    }
}