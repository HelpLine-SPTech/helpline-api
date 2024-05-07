package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.job.*;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.job.CreateJobService;
import com.helpline.helplineapi.services.job.ListJobService;
import com.helpline.helplineapi.services.job.UpdateJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final CreateJobService createJobService;
    private final ListJobService listJobService;
    private final UpdateJobService updateJobService;

    public JobController(CreateJobService createJobService, ListJobService listJobService, UpdateJobService updateJobService) {
        this.createJobService = createJobService;
        this.listJobService = listJobService;
        this.updateJobService = updateJobService;
    }

    @PostMapping
    public ResponseEntity<CreateJobResponse> create(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser, @RequestBody JobContract job) {
        var request = new CreateJobRequest();
        request.setOngId(requesterUser.getId());
        request.setJob(job);

        return createJobService.process(request);
    }

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