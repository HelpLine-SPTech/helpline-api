package com.helpline.helplineapi.services.job;

import com.helpline.helplineapi.data.contract.job.subscribe.SubscribeRequest;
import com.helpline.helplineapi.data.contract.job.subscribe.SubscribeResponse;
import com.helpline.helplineapi.entities.job.JobEntity;
import com.helpline.helplineapi.entities.job.SubscriptionEntity;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.repositories.SubscriptionRepository;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService extends BaseService<SubscribeRequest, SubscribeResponse> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    private UserEntity user;

    private JobEntity job;

    @Override
    protected SubscribeResponse processService(SubscribeRequest subscribeRequest) {
        var response = new SubscribeResponse();

        var subscription = new SubscriptionEntity();
        subscription.setVolunteer(user);
        subscription.setJob(job);

        var savedSub = subscriptionRepository.save(subscription);

        response.setSubscriptionId(savedSub.getId());

        return response;
    }

    @Override
    protected SubscribeResponse validateService(SubscribeRequest request) {
        var response = new SubscribeResponse();

        var userOpt = userRepository.findById(request.getUserId());
        if(userOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.OPERATION_NOT_PERMITTED);
            return response;
        }
        this.user = userOpt.get();

        var jobOpt = jobRepository.findById(request.getJobId());
        if(jobOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }

        this.job = jobOpt.get();

        if(this.job.getAmount() >= this.job.getVolunteers().size()) {
            response.addError(ErrorCodeEnum.JOB_FULL);
            return response;
        }

        if(this.job.getVolunteers().stream().anyMatch(v -> v.getVolunteer().getId() == request.getUserId())) {
            response.addError(ErrorCodeEnum.ALREADY_APPLIED);
            return response;
        }

        return response;
    }
}
