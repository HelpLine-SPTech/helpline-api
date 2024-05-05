package com.helpline.helplineapi.interceptors;

import com.helpline.helplineapi.infra.security.TokenService;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class RequestResponseInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BaseUserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            var token = request.getHeader("Authorization");

            if(token == null || !token.contains("Bearer ")) {
                return true;
            }

            token = token.replace("Bearer ", "");
            var requesterId = tokenService.validateToken(token);

            if(requesterId.isEmpty()) {
                return false;
            }

            var requester = userRepository
                    .findById(UUID.fromString(requesterId))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

            request.setAttribute("RequesterUser", requester);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
