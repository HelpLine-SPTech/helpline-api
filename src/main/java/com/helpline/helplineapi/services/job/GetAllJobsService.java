package com.helpline.helplineapi.services.job;

import com.helpline.helplineapi.data.contract.post.all.GetAllJobsRequest;
import com.helpline.helplineapi.data.contract.post.all.GetAllJobsResponse;
import com.helpline.helplineapi.mappers.JobMapper;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAllJobsService extends BaseService<GetAllJobsRequest, GetAllJobsResponse> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    protected GetAllJobsResponse processService(GetAllJobsRequest getAllJobsRequest) {
        var response = new GetAllJobsResponse();

        response.setJobs(JobMapper.toDto(jobRepository.findAll()));

        return response;
    }

    @Override
    protected GetAllJobsResponse validateService(GetAllJobsRequest getAllJobsRequest) {
        return new GetAllJobsResponse();
    }
}
