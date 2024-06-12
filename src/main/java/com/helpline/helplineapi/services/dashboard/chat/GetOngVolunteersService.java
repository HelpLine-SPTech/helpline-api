package com.helpline.helplineapi.services.dashboard.chat;

import com.helpline.helplineapi.data.contract.chat.GetChatUsersRequest;
import com.helpline.helplineapi.data.contract.chat.GetChatUsersResponse;
import com.helpline.helplineapi.data.contract.user.UserContract;
import com.helpline.helplineapi.entities.job.SubscriptionEntity;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetOngVolunteersService extends BaseService<GetChatUsersRequest, GetChatUsersResponse> {
  @Autowired
  private JobRepository jobRepository;

  @Override
  protected GetChatUsersResponse processService(GetChatUsersRequest request) {
    var response = new GetChatUsersResponse();
    List<SubscriptionEntity> subscriptionEntityList = new ArrayList<>();
    List<UserContract> users = new ArrayList<>();

    var jobs = jobRepository.findAllByOngId(request.getUserId());
    jobs.forEach(job -> {
      subscriptionEntityList.addAll(job.getVolunteers());
    });
    subscriptionEntityList.forEach(subscriptionEntity -> {
      users.add(UserMapper.toUserResult(subscriptionEntity.getVolunteer()));
    });
    response.setUsers(users);
    return response;
  }

  @Override
  protected GetChatUsersResponse validateService(GetChatUsersRequest getOngVolunteersRequest) {
    return new GetChatUsersResponse();
  }
}
