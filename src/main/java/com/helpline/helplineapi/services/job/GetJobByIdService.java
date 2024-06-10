package com.helpline.helplineapi.services.job;

import com.helpline.helplineapi.data.contract.job.list.GetJobByIdRequest;
import com.helpline.helplineapi.data.contract.job.list.GetJobByIdResponse;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.JobMapper;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class GetJobByIdService extends BaseService<GetJobByIdRequest, GetJobByIdResponse> {

    private final BaseUserRepository userRepository;

    private final JobRepository jobRepository;

    public GetJobByIdService(BaseUserRepository userRepository, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    protected GetJobByIdResponse processService(GetJobByIdRequest request) {
        var response = new GetJobByIdResponse();

        var jobOpt = jobRepository.findById(request.getJobId());

        if(jobOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }

        var job = jobOpt.get();

        response.setJob(JobMapper.toDto(job));

        return response;
    }

    @Override
    protected GetJobByIdResponse validateService(GetJobByIdRequest request) {
        var response = new GetJobByIdResponse();

        var userOpt = userRepository.findById(request.getRequesterUserId());

        if(userOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }

        var user = userOpt.get();

        if(!(user instanceof OngEntity)) {
            response.addError(ErrorCodeEnum.OPERATION_NOT_PERMITTED);
            return response;
        }

        return response;
    }
}
