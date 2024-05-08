package com.helpline.helplineapi.services.job;

import com.helpline.helplineapi.data.contract.job.create.CreateJobRequest;
import com.helpline.helplineapi.data.contract.job.create.CreateJobResponse;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.JobMapper;
import com.helpline.helplineapi.repositories.AddressRepository;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.repositories.OngRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobService extends BaseService<CreateJobRequest, CreateJobResponse> {

    private OngEntity ong;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    protected CreateJobResponse processService(CreateJobRequest request) {
        var response = new CreateJobResponse();
        var entity = JobMapper.toEntity(request.getJob());
        entity.setOng(ong);
        addressRepository.save(entity.getAddress());
        var savedJob = jobRepository.save(entity);

        response.setJob(JobMapper.toDto(savedJob));

        return response;
    }

    @Override
    protected CreateJobResponse validateService(CreateJobRequest request) {
        var response = new CreateJobResponse();

        var ongOptional = ongRepository.findById(request.getOngId());

        if(ongOptional.isEmpty()) response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
        else ong = ongOptional.get();

        return response;
    }
}
