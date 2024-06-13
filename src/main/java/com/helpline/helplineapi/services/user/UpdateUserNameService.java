package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.user.updateName.UpdateUserNameRequest;
import com.helpline.helplineapi.data.contract.user.updateName.UpdateUserNameResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserNameService extends BaseService<UpdateUserNameRequest, UpdateUserNameResponse> {

    @Autowired
    private BaseUserRepository userRepository;

    private BaseUserEntity user;

    @Override
    protected UpdateUserNameResponse processService(UpdateUserNameRequest request) {
        user.setName(request.getName());
        userRepository.save(user);

        return new UpdateUserNameResponse();
    }

    @Override
    protected UpdateUserNameResponse validateService(UpdateUserNameRequest request) {
        var response = new UpdateUserNameResponse();

        if(request.getName().isEmpty()) {
            response.addError(ErrorCodeEnum.INVALID_PROPERTY);
            return response;
        }

        var userOpt = userRepository.findById(request.getUserId());

        if(userOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }
        user = userOpt.get();

        return response;
    }
}
