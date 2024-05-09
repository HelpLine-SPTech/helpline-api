package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.user.auth.login.LoginRequest;
import com.helpline.helplineapi.data.contract.user.auth.login.LoginResponse;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterRequest;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterResponse;
import com.helpline.helplineapi.data.contract.user.list.UserListRequest;
import com.helpline.helplineapi.data.contract.user.list.UserListResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.UserTypeEnum;
import com.helpline.helplineapi.infra.security.TokenService;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.services.user.AuthService;
import com.helpline.helplineapi.services.user.ListUserService;
import com.helpline.helplineapi.services.user.LoginService;
import com.helpline.helplineapi.services.user.RegisterUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

@CrossOrigin
@RestController()
@RequestMapping("auth")
@SecurityRequirement(name = "helpline-api")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    LoginService loginService;

    @Autowired
    ListUserService listUserService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest body) {
        return loginService.process(body);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest body) {
        return registerUserService.process(body);
    }

    @GetMapping()
    public ResponseEntity<UserListResponse> list(
            @RequestParam int type,
            @RequestParam(defaultValue = "1") int algorithm,
            @RequestParam(defaultValue = "1") int order) {

        var request = new UserListRequest(
                UserTypeEnum.fromInteger(type),
                algorithm,
                order
        );

        return listUserService.process(request);
    }

    @GetMapping(value = "/report", produces = "text/csv")
    public ResponseEntity<FileSystemResource> report() throws IOException {
        var sb = new StringBuilder();

        String[] headers = {"Nome", "Tamanho"};

        String[][] data = {{"Renan", "tamanho teste 1"}, {"Leo", "tamanho teste 2"}};

        var sj1 = new StringJoiner(";");
        for (String header : headers) {
            sj1.add(header);
        }

        sb.append(sj1 + "\n");

        for(String[] d : data) {
            var sj2 = new StringJoiner(";");
            for (String s : d) {
                sj2.add(s);
            }
            sb.append(sj2 + "\n");
        }

        FileWriter file = new FileWriter("report.csv");
        file.write(sb.toString());
        file.close();
        var fileResource = new FileSystemResource("report.csv");

        MediaType mediaType = MediaTypeFactory
                .getMediaType(fileResource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        // 3
        ContentDisposition disposition = ContentDisposition
                // 3.2
                .attachment() // or .attachment()
                // 3.1
                .filename(fileResource.getFilename())
                .build();
        httpHeaders.setContentDisposition(disposition);

        return new ResponseEntity<FileSystemResource>(fileResource, httpHeaders, HttpStatus.OK);
    }
}
