package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.job.*;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.mappers.UserMapper;
import com.helpline.helplineapi.services.job.CreateJobService;
import com.helpline.helplineapi.services.job.DeleteJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final CreateJobService createJobService;
    private final DeleteJobService deleteJobService;

    public JobController(CreateJobService createJobService,DeleteJobService deleteJobService  ) {
        this.createJobService = createJobService;
        this.deleteJobService = deleteJobService;
    }

    @PostMapping
    public ResponseEntity<CreateJobResponse> create(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser, @RequestBody JobContract job) {
        var request = new CreateJobRequest();
        request.setOngId(requesterUser.getId());
        request.setJob(job);

        return createJobService.process(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteJobResponse> delete(@PathVariable UUID id){
        DeleteJobRequest request = new DeleteJobRequest();
        request.setId(id);
        return deleteJobService.process(request);
    }
}