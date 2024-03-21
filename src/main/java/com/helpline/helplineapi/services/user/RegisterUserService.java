package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.user.auth.register.RegisterRequest;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterResponse;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService extends BaseService<RegisterRequest, RegisterResponse> {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected RegisterResponse processService(RegisterRequest request) {
        var response = new RegisterResponse();

        var password = passwordEncoder.encode(request.password());

        var user = new UserEntity(request.email(), password, request.name(), request.document(), request.type(), request.role());

        response.setSavedUser(repository.save(user));

        return response;
    }

    @Override
    protected RegisterResponse validateService(RegisterRequest request) {
        var response = new RegisterResponse();

        if(repository.findByEmail(request.email()) != null) {
            response.addError(ErrorCodeEnum.USER_ALREADY_EXISTS_ERROR);
            return response;
        }

        return response;
    }
}
