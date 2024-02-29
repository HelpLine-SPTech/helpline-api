package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.auth.login.LoginRequest;
import com.helpline.helplineapi.data.contract.auth.login.LoginResponse;
import com.helpline.helplineapi.data.contract.auth.register.RegisterRequest;
import com.helpline.helplineapi.data.contract.auth.register.RegisterResponse;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.infra.security.TokenService;
import com.helpline.helplineapi.services.user.AuthService;
import com.helpline.helplineapi.services.user.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequest body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Validated RegisterRequest body) {
        return registerUserService.process(body);
    }

    @GetMapping("/hello")
    private ResponseEntity<String> hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return ResponseEntity.ok(String.format("Hello %s", name));
    }

    @PostMapping("/nome")
    private ResponseEntity testEndpoint(@RequestBody String nome) {
        System.out.println(nome);
        return ResponseEntity.ok().build();
    }
}
