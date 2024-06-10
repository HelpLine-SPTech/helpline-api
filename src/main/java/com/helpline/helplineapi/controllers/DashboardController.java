package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.dashboard.SummaryRequest;
import com.helpline.helplineapi.data.contract.dashboard.SummaryResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.dashboard.SummaryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/dashboard")
@SecurityRequirement(name = "helpline-api")
public class DashboardController {

    @Autowired
    private SummaryService summaryService;

    @GetMapping
    public ResponseEntity<SummaryResponse> getSummary(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser) {
        var request = new SummaryRequest();
        request.setOngId(requesterUser.getId());

        return summaryService.process(request);
    }

}
