package com.helpline.helplineapi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InstanceInfoController {

    @GetMapping("/proxy-info")
    public String getProxyInfo(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For"); // IP original pelo proxy
        String clientIp = (forwardedFor != null) ? forwardedFor : request.getRemoteAddr();
        int clientPort = request.getRemotePort();
        return "Client IP: " + clientIp + ", Client Port: " + clientPort;
    }
}

