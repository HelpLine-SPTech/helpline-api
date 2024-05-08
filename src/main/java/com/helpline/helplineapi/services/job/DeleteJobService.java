package com.helpline.helplineapi.services.job;
import com.helpline.helplineapi.data.contract.job.delete.DeleteJobRequest;
import com.helpline.helplineapi.data.contract.job.delete.DeleteJobResponse;
import com.helpline.helplineapi.entities.job.JobEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.JobMapper;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteJobService extends BaseService <DeleteJobRequest, DeleteJobResponse> {
    @Autowired
    private JobRepository repository;

    private JobEntity job;

    @Override
    protected DeleteJobResponse processService(DeleteJobRequest deleteJobRequest) {
        var response = new DeleteJobResponse();
        response.setJob(JobMapper.toDto(job));
        repository.deleteById(deleteJobRequest.getId());
        return response;
    }


    @Override
    protected DeleteJobResponse validateService(DeleteJobRequest deleteJobRequest) {
        var response = new DeleteJobResponse();

        var jobOpt = repository.findById(deleteJobRequest.getId());

        if(jobOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
        } else job = jobOpt.get();

        return response;

    }

}
