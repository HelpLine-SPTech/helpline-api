package com.helpline.helplineapi.data.contract.donations.report;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class GetDonationReportRequest {
    private UUID ongId;
}
