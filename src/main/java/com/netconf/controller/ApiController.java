package com.netconf.controller;

import com.netconf.dto.ConnectionRequest;
import com.netconf.dto.EditConfigRequest;
import com.netconf.dto.GetConfigRequest;
import com.netconf.model.NetconfResponse;
import com.netconf.model.NetconfSession;
import com.netconf.service.NetconfService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/netconf")
@CrossOrigin(origins = "*")
public class ApiController {
    private final NetconfService netconfService;

    @Autowired
    public ApiController(NetconfService netconfService) {
        this.netconfService = netconfService;
    }

    @PostMapping("/connect")
    public ResponseEntity<NetconfResponse> connect(@Valid @RequestBody ConnectionRequest request) {
        NetconfSession session = netconfService.connect(
            request.getHost(),
            request.getPort(),
            request.getUsername(),
            request.getPassword()
        );
        return ResponseEntity.ok(new NetconfResponse(true, "Connected successfully", session));
    }

    @PostMapping("/disconnect")
    public ResponseEntity<NetconfResponse> disconnect(@RequestParam String sessionId) {
        netconfService.disconnect(sessionId);
        return ResponseEntity.ok(new NetconfResponse(true, "Disconnected successfully"));
    }

    @PostMapping("/get-config")
    public ResponseEntity<NetconfResponse> getConfig(@Valid @RequestBody GetConfigRequest request) {
        String config = netconfService.getConfig(
            request.getSessionId(),
            request.getDatastore(),
            request.getFilter()
        );
        return ResponseEntity.ok(new NetconfResponse(true, "Configuration retrieved", config));
    }

    @PostMapping("/edit-config")
    public ResponseEntity<NetconfResponse> editConfig(@Valid @RequestBody EditConfigRequest request) {
        boolean success = netconfService.editConfig(
            request.getSessionId(),
            request.getDatastore(),
            request.getConfig(),
            request.getDefaultOperation()
        );
        return ResponseEntity.ok(new NetconfResponse(success, "Configuration edited successfully"));
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<NetconfSession>> getSessions() {
        return ResponseEntity.ok(netconfService.getActiveSessions());
    }

    @GetMapping("/capabilities/{sessionId}")
    public ResponseEntity<NetconfResponse> getCapabilities(@PathVariable String sessionId) {
        List<String> capabilities = netconfService.getCapabilities(sessionId);
        return ResponseEntity.ok(new NetconfResponse(true, "Capabilities retrieved", capabilities));
    }
}