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

    @Autowired
    private Environment environment;
    @GetMapping("/instance-info")
    public ResponseEntity<String> getInstanceInfo() {
        String message = "Respondendo pela instância na porta: " + environment.getProperty("local.server.port");
        return ResponseEntity.ok(message);

}

