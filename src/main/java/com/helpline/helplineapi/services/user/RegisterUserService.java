package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.user.auth.register.RegisterRequest;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.enums.UserTypeEnum;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.OngRepository;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService extends BaseService<RegisterRequest, RegisterResponse> {

    @Autowired
    private BaseUserRepository repository;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected RegisterResponse processService(RegisterRequest request) {
        var response = new RegisterResponse();

        if(request.getType() == UserTypeEnum.ONG) return registerOng(request, response);
        else return registerUser(request, response);
    }


    @Override
    protected RegisterResponse validateService(RegisterRequest request) {
        var response = new RegisterResponse();

        if(repository.findByEmail(request.getEmail()) != null) {
            response.addError(ErrorCodeEnum.USER_ALREADY_EXISTS_ERROR);
            return response;
        }

        return response;
    }

    private RegisterResponse registerOng(RegisterRequest request, RegisterResponse response) {
        var password = passwordEncoder.encode(request.getPassword());
        var ong = new OngEntity();
        ong.setEmail(request.getEmail());
        ong.setPassword(password);
        ong.setName(request.getName());
        ong.setDocument(request.getDocument());
        ong.setRole(request.getRole());

        ongRepository.save(ong);

        response.setSavedUser(ong);
        return response;
    }
    private RegisterResponse registerUser(RegisterRequest request, RegisterResponse response) {
        var password = passwordEncoder.encode(request.getPassword());
        var user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(password);
        user.setName(request.getName());
        user.setDocument(request.getDocument());
        user.setRole(request.getRole());

        userRepository.save(user);

        response.setSavedUser(user);
        return response;
    }
}
