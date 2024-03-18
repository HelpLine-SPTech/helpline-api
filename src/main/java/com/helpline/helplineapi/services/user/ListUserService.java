package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.user.list.UserListRequest;
import com.helpline.helplineapi.data.contract.user.list.UserListResponse;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListUserService extends BaseService<UserListRequest, UserListResponse> {
    @Autowired
    private UserRepository repository;

    @Override
    protected UserListResponse processService(UserListRequest request) {
        var response = new UserListResponse();

        var users = repository.findByType(request.getType());

        return response;
    }

    @Override
    protected UserListResponse validateService(UserListRequest request) {
        return new UserListResponse();
    }
}
