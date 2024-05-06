package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.job.*;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.services.job.CreateJobService;
import com.helpline.helplineapi.services.job.UpdateJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final CreateJobService createJobService;
    private final UpdateJobService updateJobService;

    public JobController(CreateJobService createJobService, UpdateJobService updateJobService) {
        this.createJobService = createJobService;
        this.updateJobService = updateJobService;
    }

    @PostMapping
    public ResponseEntity<CreateJobResponse> create(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser, @RequestBody JobContract job) {
        var request = new CreateJobRequest();
        request.setOngId(requesterUser.getId());
        request.setJob(job);

        return createJobService.process(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateJobResponse> update(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser, @PathVariable UUID id, @RequestBody JobContract job) {
        var request = new UpdateJobRequest();
        request.setOngId(requesterUser.getId());
        request.setJob(job);
        request.setJobId(id);

        return updateJobService.process(request);
    }
}