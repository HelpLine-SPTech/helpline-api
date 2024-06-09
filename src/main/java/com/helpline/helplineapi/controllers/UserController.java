package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.user.auth.login.LoginRequest;
import com.helpline.helplineapi.data.contract.user.auth.login.LoginResponse;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterRequest;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterResponse;
import com.helpline.helplineapi.data.contract.user.list.UserListRequest;
import com.helpline.helplineapi.data.contract.user.list.UserListResponse;
import com.helpline.helplineapi.data.contract.user.profilepic.UpdateProfilePicRequest;
import com.helpline.helplineapi.data.contract.user.profilepic.UpdateProfilePicResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.UserTypeEnum;
import com.helpline.helplineapi.services.user.ListUserService;
import com.helpline.helplineapi.services.user.LoginService;
import com.helpline.helplineapi.services.user.RegisterUserService;
import com.helpline.helplineapi.services.user.profilepic.UpdateProfilePicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    UpdateProfilePicService updateProfilePicService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest body) {
        return loginService.process(body);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest body) {
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

    @PatchMapping("/profile")
    public ResponseEntity<UpdateProfilePicResponse> uploadProfilePic(
            @RequestAttribute("RequesterUser") BaseUserEntity requesterUser,
            @RequestParam("file") MultipartFile file) {
        var request = new UpdateProfilePicRequest();
        request.setUserId(requesterUser.getId());
        request.setFile(file);

        return updateProfilePicService.process(request);
    }
}
