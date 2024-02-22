package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.auth.register.RegisterRequest;
import com.helpline.helplineapi.data.contract.auth.register.RegisterResponse;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService extends BaseService<RegisterRequest, RegisterResponse> {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected ResponseEntity<RegisterResponse> processService(RegisterRequest request) {
        var response = new RegisterResponse();

        if(repository.findByEmail(request.email()) != null) return ResponseEntity.badRequest().build();

        var password = passwordEncoder.encode(request.password());

        var user = new UserEntity(request.email(), password, request.role());

        response.setSavedUser(repository.save(user));

        return ResponseEntity.ok(response);
    }
}
