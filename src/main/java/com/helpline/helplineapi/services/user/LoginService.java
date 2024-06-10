package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.user.auth.login.LoginRequest;
import com.helpline.helplineapi.data.contract.user.auth.login.LoginResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.infra.security.TokenService;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends BaseService<LoginRequest, LoginResponse> {

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    private Authentication auth;

    @Override
    protected LoginResponse processService(LoginRequest loginRequest) {
        var response = new LoginResponse();

        var user = (BaseUserEntity) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        response.setUser(UserMapper.toUserResult(user));
        response.setToken(token);

        return response;
    }

    @Override
    protected LoginResponse validateService(LoginRequest loginRequest) {
        var response = new LoginResponse();
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        try {
            auth = authenticationManager.authenticate(usernamePassword);
        } catch(InternalAuthenticationServiceException ex) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }

        return new LoginResponse();
    }
}
