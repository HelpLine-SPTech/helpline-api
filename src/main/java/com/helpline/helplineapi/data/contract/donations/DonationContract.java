package com.helpline.helplineapi.data.contract.donations;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class DonationContract {
    private UUID id;

    private Long amount;

    private LocalDateTime donationDate;

    private Integer quantity;

    private UUID donorId;

    private UUID campaignId;

    private boolean isConfirmed;
}
