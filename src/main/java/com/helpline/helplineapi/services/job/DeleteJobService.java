package com.helpline.helplineapi.services.job;
import com.helpline.helplineapi.data.contract.job.CreateJobResponse;
import com.helpline.helplineapi.data.contract.job.DeleteJobRequest;
import com.helpline.helplineapi.data.contract.job.DeleteJobResponse;
import com.helpline.helplineapi.mappers.JobMapper;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteJobService extends BaseService <DeleteJobRequest, DeleteJobResponse> {
    @Autowired
    private JobRepository repository;
    @Override
    protected DeleteJobResponse processService(DeleteJobRequest deleteJobRequest) {
        var response = new DeleteJobResponse();
        var job = repository.findById(deleteJobRequest.getId()).get();
        response.setJob(JobMapper.toDto(job));
        repository.deleteById(deleteJobRequest.getId());
        return response;
    }


    @Override
    protected DeleteJobResponse validateService(DeleteJobRequest deleteJobRequest) {
        return new DeleteJobResponse();
    }

}
