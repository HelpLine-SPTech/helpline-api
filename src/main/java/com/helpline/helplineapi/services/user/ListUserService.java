package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.data.contract.user.list.UserListRequest;
import com.helpline.helplineapi.data.contract.user.list.UserListResponse;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import com.helpline.helplineapi.strategy.BubbleSortStrategy;
import com.helpline.helplineapi.strategy.ISortStrategy;
import com.helpline.helplineapi.strategy.QuickSortStrategy;
import com.helpline.helplineapi.strategy.SelectionSortStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUserService extends BaseService<UserListRequest, UserListResponse> {
    @Autowired
    private UserRepository repository;

    private ISortStrategy<UserEntity> sortStrategy = new QuickSortStrategy<UserEntity>();

    @Override
    protected UserListResponse processService(UserListRequest request) {
        var response = new UserListResponse();

        var users = repository.findByType(request.getType());

        if(request.getOrder() == 1) sortStrategy.sort((List<UserEntity>) users);
        else sortStrategy.sortDescending((List<UserEntity>) users);

        return response;
    }

    @Override
    protected UserListResponse validateService(UserListRequest request) {
        var response = new UserListResponse();

        switch(request.getSortStrategy()) {
            case 1 -> this.sortStrategy = new BubbleSortStrategy<>();
            case 2 -> this.sortStrategy = new SelectionSortStrategy<>();
            case 3 -> this.sortStrategy = new QuickSortStrategy<>();
        }

        return response;
    }
}
