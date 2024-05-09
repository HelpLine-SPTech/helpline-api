package com.helpline.helplineapi.data.contract.donations.report;

import com.helpline.helplineapi.data.contract.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.FileSystemResource;

@Getter @Setter
public class GetDonationReportResponse extends BaseResponse {
    private FileSystemResource csv;
}
