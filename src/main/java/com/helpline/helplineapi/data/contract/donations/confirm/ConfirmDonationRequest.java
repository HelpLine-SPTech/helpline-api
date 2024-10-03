package com.helpline.helplineapi.data.contract.donations.confirm;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ConfirmDonationRequest {
    private UUID requesterUserId;

    private UUID donationId;
}
