package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.job.*;
import com.helpline.helplineapi.data.contract.job.create.CreateJobRequest;
import com.helpline.helplineapi.data.contract.job.create.CreateJobResponse;
import com.helpline.helplineapi.data.contract.job.delete.DeleteJobRequest;
import com.helpline.helplineapi.data.contract.job.delete.DeleteJobResponse;
import com.helpline.helplineapi.data.contract.job.list.ListJobRequest;
import com.helpline.helplineapi.data.contract.job.list.ListJobResponse;
import com.helpline.helplineapi.data.contract.job.update.UpdateJobRequest;
import com.helpline.helplineapi.data.contract.job.update.UpdateJobResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.job.CreateJobService;
import com.helpline.helplineapi.services.job.DeleteJobService;
import com.helpline.helplineapi.services.job.ListJobService;
import com.helpline.helplineapi.services.job.UpdateJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final CreateJobService createJobService;
    private final UpdateJobService updateJobService;
    private final ListJobService listJobService;

    private final DeleteJobService deleteJobService;

    public JobController(CreateJobService createJobService, UpdateJobService updateJobService, ListJobService listJobService, DeleteJobService deleteJobService) {
        this.createJobService = createJobService;
        this.updateJobService = updateJobService;
        this.listJobService = listJobService;
        this.deleteJobService = deleteJobService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteJobResponse> delete(@PathVariable UUID id) {
        var request = new DeleteJobRequest();
        request.setId(id);

        return deleteJobService.process(request);
    }
}