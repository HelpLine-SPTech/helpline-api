package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.donations.confirm.ConfirmDonationRequest;
import com.helpline.helplineapi.data.contract.donations.confirm.ConfirmDonationResponse;
import com.helpline.helplineapi.data.contract.donations.report.GetDonationReportRequest;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.donation.ConfirmDonationService;
import com.helpline.helplineapi.services.donation.DonationReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/donations")
@SecurityRequirement(name = "helpline-api")
public class DonationController {

    @Autowired
    private DonationReportService donationReportService;

    @Autowired
    private ConfirmDonationService confirmDonationService;

    @GetMapping("/report")
    public ResponseEntity<FileSystemResource> donationsReport(@RequestAttribute("RequesterUser")BaseUserEntity requester) {
        var request = new GetDonationReportRequest();
        request.setOngId(requester.getId());
        var response = donationReportService.process(request).getBody();

        MediaType mediaType = MediaTypeFactory
                .getMediaType(response.getCsv())
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        httpHeaders.setAccessControlExposeHeaders(List.of("Content-Disposition"));
        httpHeaders.setAccessControlExposeHeaders(List.of("Content-Disposition"));
        ContentDisposition disposition = ContentDisposition
                .attachment()
                .filename(response.getCsv().getFilename())
                .build();
        httpHeaders.setContentDisposition(disposition);

        return new ResponseEntity<FileSystemResource>(response.getCsv(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<ConfirmDonationResponse> confirm(@PathVariable UUID id, @RequestAttribute("RequesterUser")BaseUserEntity requester) {
        var request = new ConfirmDonationRequest();
        request.setDonationId(id);
        request.setRequesterUserId(requester.getId());

        return confirmDonationService.process(request);
    }
}
