package com.helpline.helplineapi.controllers;
<<<<<<< HEAD
=======

>>>>>>> f6cf5185c8f57364806dc03efc73832cdb24d782
import com.helpline.helplineapi.data.contract.job.*;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.job.CreateJobService;
<<<<<<< HEAD
import com.helpline.helplineapi.services.job.ListJobService;
=======
import com.helpline.helplineapi.services.job.UpdateJobService;
>>>>>>> f6cf5185c8f57364806dc03efc73832cdb24d782
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final CreateJobService createJobService;
<<<<<<< HEAD
    private final ListJobService listJobService;

    public JobController(CreateJobService createJobService, ListJobService listJobService) {
        this.createJobService = createJobService;
        this.listJobService = listJobService;
=======
    private final UpdateJobService updateJobService;

    public JobController(CreateJobService createJobService, UpdateJobService updateJobService) {
        this.createJobService = createJobService;
        this.updateJobService = updateJobService;
>>>>>>> f6cf5185c8f57364806dc03efc73832cdb24d782
    }

    @PostMapping
    public ResponseEntity<CreateJobResponse> create(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser, @RequestBody JobContract job) {
        var request = new CreateJobRequest();
        request.setOngId(requesterUser.getId());
        request.setJob(job);

        return createJobService.process(request);
    }

<<<<<<< HEAD
    @GetMapping
    public ResponseEntity<ListJobResponse> listJobs(
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestAttribute("RequesterUser") BaseUserEntity requesterUser){

        ListJobRequest request = new ListJobRequest();
        request.setSort(sort);
        request.setOrder(order);
        request.setPage(page);
        request.setSize(size);
        request.setOngId(requesterUser.getId());

        return listJobService.process(request);
=======
    @PutMapping("/{id}")
    public ResponseEntity<UpdateJobResponse> update(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser, @PathVariable UUID id, @RequestBody JobContract job) {
        var request = new UpdateJobRequest();
        request.setOngId(requesterUser.getId());
        request.setJob(job);
        request.setJobId(id);

        return updateJobService.process(request);
>>>>>>> f6cf5185c8f57364806dc03efc73832cdb24d782
    }
}