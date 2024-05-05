package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.user.list.UserListRequest;
import com.helpline.helplineapi.data.contract.user.list.UserListResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.services.BaseService;
import com.helpline.helplineapi.strategy.BubbleSortStrategy;
import com.helpline.helplineapi.strategy.ISortStrategy;
import com.helpline.helplineapi.strategy.QuickSortStrategy;
import com.helpline.helplineapi.strategy.SelectionSortStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ListUserService extends BaseService<UserListRequest, UserListResponse> {
    @Autowired
    private BaseUserRepository repository;

    private ISortStrategy<BaseUserEntity> sortStrategy = new QuickSortStrategy<BaseUserEntity>();

    @Override
    protected UserListResponse processService(UserListRequest request) {
        var response = new UserListResponse();

        var users = new ArrayList<>(repository.findAll());

        if(request.getOrder() == 1) sortStrategy.sort(users);
        else sortStrategy.sortDescending(users);

        response.setUsers(UserMapper.toUserResult(users));

        return response;
    }

    @Override
    protected UserListResponse validateService(UserListRequest request) {
        var response = new UserListResponse();

        switch(request.getSortStrategy()) {
            case 1 -> this.sortStrategy = new BubbleSortStrategy<>();
            case 2 -> this.sortStrategy = new SelectionSortStrategy<>();
            case 3 -> this.sortStrategy = new QuickSortStrategy<>();
            default -> response.addError(ErrorCodeEnum.INVALID_PROPERTY);
        }

        return response;
    }
}
