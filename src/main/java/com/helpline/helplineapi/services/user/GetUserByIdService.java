package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.user.getById.GetUserByIdRequest;
import com.helpline.helplineapi.data.contract.user.getById.GetUserByIdResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdService extends BaseService<GetUserByIdRequest, GetUserByIdResponse> {

    @Autowired
    private BaseUserRepository userRepository;

    private BaseUserEntity user;

    @Override
    protected GetUserByIdResponse processService(GetUserByIdRequest request) {
        var response = new GetUserByIdResponse();

        response.setUser(UserMapper.toUserResult(user));

        return response;
    }

    @Override
    protected GetUserByIdResponse validateService(GetUserByIdRequest request) {
        var response = new GetUserByIdResponse();

        var userOpt = userRepository.findById(request.getId());

        if(userOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }
        user = userOpt.get();

        return response;
    }
}
