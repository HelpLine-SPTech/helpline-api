package com.helpline.helplineapi.data.contract.donations.create;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.donations.DonationContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateDonationResponse extends BaseResponse {
    private DonationContract donation;
}
