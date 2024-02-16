package com.helpline.helplineapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/hello")
    private String hello(@RequestPara(name = "name", defaultValue = "World") String name) {
        return String.format("Hello %s", name);
    }
}
