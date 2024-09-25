package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import com.helpline.helplineapi.data.contract.campaign.create.CreateCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.create.CreateCampaignResponse;
import com.helpline.helplineapi.data.contract.campaign.delete.DeleteCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.delete.DeleteCampaignResponse;
import com.helpline.helplineapi.data.contract.campaign.get.id.GetCampaignByIdRequest;
import com.helpline.helplineapi.data.contract.campaign.get.id.GetCampaignByIdResponse;
import com.helpline.helplineapi.data.contract.campaign.get.list.ListCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.get.list.ListCampaignResponse;
import com.helpline.helplineapi.data.contract.campaign.update.UpdateCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.update.UpdateCampaignResponse;
import com.helpline.helplineapi.data.contract.job.list.ListJobRequest;
import com.helpline.helplineapi.data.contract.job.list.ListJobResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.campaign.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/campaigns")
@SecurityRequirement(name = "helpline-api")
public class CampaignController {

    @Autowired
    private CreateCampaignService createCampaignService;

    @Autowired
    private UpdateCampaignService updateCampaignService;

    @Autowired
    private GetCampaignByIdService getCampaignByIdService;

    @Autowired
    private ListCampaignService listCampaignService;

    @Autowired
    private DeleteCampaignService deleteCampaignService;

    @PostMapping
    public ResponseEntity<CreateCampaignResponse> create(
            @RequestAttribute("RequesterUser") BaseUserEntity requesterUser,
            @RequestBody CampaignContract campaign) {

        var request = new CreateCampaignRequest();
        request.setOngId(requesterUser.getId());
        request.setCampaign(campaign);

        return createCampaignService.process(request);
    }

    @GetMapping
    public ResponseEntity<ListCampaignResponse> listJobs(
            @RequestParam(defaultValue = "addedAt") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String desc,
            @RequestAttribute("RequesterUser") BaseUserEntity requesterUser){

        var request = new ListCampaignRequest();
        request.setSort(sort);
        request.setOrder(order);
        request.setPage(page);
        request.setSize(size);
        request.setDesc(desc);
        request.setOngId(requesterUser.getId());

        return listCampaignService.process(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCampaignByIdResponse> getById(
            @RequestAttribute("RequesterUser") BaseUserEntity requesterUser,
            @PathVariable UUID id
    ) {
        var request = new GetCampaignByIdRequest();
        request.setCampaignId(id);
        request.setUser(requesterUser);

        return getCampaignByIdService.process(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCampaignResponse> update(
            @RequestAttribute("RequesterUser") BaseUserEntity requesterUser,
            @PathVariable UUID id,
            @RequestBody CampaignContract campaign
            ) {
        var request = new UpdateCampaignRequest();
        request.setUser(requesterUser);
        request.setCampaignId(id);
        request.setCampaign(campaign);

        return updateCampaignService.process(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCampaignResponse> delete(
            @RequestAttribute("RequesterUser") BaseUserEntity requesterUser,
            @PathVariable UUID id) {
        var request = new DeleteCampaignRequest();
        request.setCampaignId(id);
        request.setUser(requesterUser);

        return deleteCampaignService.process(request);
    }
}
