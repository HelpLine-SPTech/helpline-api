package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.user.auth.login.LoginRequest;
import com.helpline.helplineapi.data.contract.user.auth.login.LoginResponse;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterRequest;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterResponse;
import com.helpline.helplineapi.data.contract.user.list.UserListRequest;
import com.helpline.helplineapi.data.contract.user.list.UserListResponse;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.UserTypeEnum;
import com.helpline.helplineapi.infra.security.TokenService;
import com.helpline.helplineapi.services.user.AuthService;
import com.helpline.helplineapi.services.user.ListUserService;
import com.helpline.helplineapi.services.user.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("auth")
@CrossOrigin
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    AuthService authService;

    @Autowired
    ListUserService listUserService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequest body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest body) {
        return registerUserService.process(body);
    }

    @GetMapping()
    public ResponseEntity<UserListResponse> list(@RequestParam int type) {
        var request = new UserListRequest(
                UserTypeEnum.fromInteger(type)
        );

        return listUserService.process(request);
    }
}
