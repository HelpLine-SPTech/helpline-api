package com.helpline.helplineapi.services.job;

import com.helpline.helplineapi.data.contract.job.UpdateJobRequest;
import com.helpline.helplineapi.data.contract.job.UpdateJobResponse;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.JobMapper;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateJobService extends BaseService<UpdateJobRequest, UpdateJobResponse> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    protected UpdateJobResponse processService(UpdateJobRequest updateJobRequest) {
        var response = new UpdateJobResponse();
        var oldEntity = jobRepository.findById(updateJobRequest.getJobId()).get();
        var newEntity = JobMapper.merge(updateJobRequest.getJob(), oldEntity);
        jobRepository.save(newEntity);
        response.setJob(JobMapper.toDto(newEntity));
        return response;
    }



    @Override
    protected UpdateJobResponse validateService(UpdateJobRequest updateJobRequest) {
        var response = new UpdateJobResponse();
        var oldEntity = jobRepository.findById(updateJobRequest.getJobId());
        if (oldEntity.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }
        return response;
    }
}
