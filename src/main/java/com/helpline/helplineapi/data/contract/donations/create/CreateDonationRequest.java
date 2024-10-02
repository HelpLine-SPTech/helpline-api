package com.helpline.helplineapi.data.contract.donations.create;

import com.helpline.helplineapi.data.contract.donations.DonationContract;
import com.helpline.helplineapi.data.contract.user.UserContract;
import com.helpline.helplineapi.entities.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateDonationRequest {
    private DonationContract donation;

    private UserEntity donor;
}
