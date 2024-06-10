package com.helpline.helplineapi.services.job;

import com.helpline.helplineapi.data.contract.job.*;
import com.helpline.helplineapi.data.contract.job.list.ListJobRequest;
import com.helpline.helplineapi.data.contract.job.list.ListJobResponse;
import com.helpline.helplineapi.entities.job.JobEntity;
import com.helpline.helplineapi.mappers.JobMapper;
import com.helpline.helplineapi.repositories.JobRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListJobService extends BaseService<ListJobRequest, ListJobResponse> {
    @Autowired
    private JobRepository repository;

    @Override
    protected ListJobResponse processService(ListJobRequest listJobRequest) {
        Sort.Direction direction = listJobRequest.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(listJobRequest.getPage() - 1, listJobRequest.getSize(), Sort.by(direction, listJobRequest.getSort()));
        Page<JobEntity> jobEntities = repository.findAllByOngId(listJobRequest.getOngId(), pageable);


        List<JobContract> jobContracts = JobMapper.toDto(jobEntities.getContent());
        if(!listJobRequest.getDesc().isEmpty()) {
            jobContracts = jobContracts
                    .stream()
                    .filter(j -> j.getDescription().toLowerCase().contains(listJobRequest.getDesc().toLowerCase()) || j.getTitle().toLowerCase().contains(listJobRequest.getDesc().toLowerCase()))
                    .toList();
        }

        var response = new ListJobResponse();
        response.setJobs(jobContracts);
        response.setTotalPages(jobEntities.getTotalPages());
        response.setTotalElements(jobEntities.getTotalElements());
        return response;
    }

    @Override
    protected ListJobResponse validateService(ListJobRequest listJobRequest) {
        return new ListJobResponse();
    }
}