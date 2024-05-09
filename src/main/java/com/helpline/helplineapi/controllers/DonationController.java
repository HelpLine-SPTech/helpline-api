package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.donations.report.GetDonationReportRequest;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.donation.DonationReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donations")
@SecurityRequirement(name = "helpline-api")
public class DonationController {

    @Autowired
    private DonationReportService donationReportService;

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

        ContentDisposition disposition = ContentDisposition
                .attachment()
                .filename(response.getCsv().getFilename())
                .build();
        httpHeaders.setContentDisposition(disposition);

        return new ResponseEntity<FileSystemResource>(response.getCsv(), httpHeaders, HttpStatus.OK);
    }
}
