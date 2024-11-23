package com.helpline.helplineapi.services.Search;

import com.helpline.helplineapi.data.contract.search.SearchUserRequest;
import com.helpline.helplineapi.data.contract.search.SearchUserResponse;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchUserService extends BaseService<SearchUserRequest, SearchUserResponse> {

    @Autowired
    private BaseUserRepository userRepository;

    @Override
    protected SearchUserResponse processService(SearchUserRequest request) {
        var response = new SearchUserResponse();

        var users = userRepository.findByNameContainsIgnoreCase(request.getName());
        response.setUsers(UserMapper.toUserResult(users));

        return response;
    }

    @Override
    protected SearchUserResponse validateService(SearchUserRequest request) {
        return new SearchUserResponse();
    }
}
