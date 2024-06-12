package com.helpline.helplineapi.services.dashboard.chat;

import com.helpline.helplineapi.data.contract.chat.GetChatUsersRequest;
import com.helpline.helplineapi.data.contract.chat.GetChatUsersResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.repositories.OngRepository;
import com.helpline.helplineapi.repositories.SubscriptionRepository;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class GetVolunteerOngsService extends BaseService<GetChatUsersRequest, GetChatUsersResponse> {

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private UserEntity user;

    @Override
    protected GetChatUsersResponse processService(GetChatUsersRequest request) {
        var response = new GetChatUsersResponse();

        var subscriptions = subscriptionRepository.findByVolunteerId(user.getId());

        HashSet<BaseUserEntity> ongs = new HashSet<>();
        subscriptions.forEach(s -> {
            var job = s.getJob();
            var ong = job.getOng();
            ongs.add(ong);
        });

        response.setUsers(UserMapper.toUserResult(ongs.stream().toList()));
        return response;
    }

    @Override
    protected GetChatUsersResponse validateService(GetChatUsersRequest request) {
        var response = new GetChatUsersResponse();

        var userOpt = userRepository.findById(request.getUserId());

        if(userOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }

        this.user = userOpt.get();

        return response;
    }
}
