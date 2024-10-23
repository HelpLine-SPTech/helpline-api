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
        String clientIp = request.getRemoteAddr(); // IP do cliente
        int clientPort = request.getRemotePort();  // Porta do cliente

        // Captura a porta local que o container está usando (ex: 8080 ou 8081)
        int localPort = request.getLocalPort();  

        return "Client IP: " + clientIp + 
               ", Client Port: " + clientPort + 
               ", Local Port (Container): " + localPort;
    }
}

