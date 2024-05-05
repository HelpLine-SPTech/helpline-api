package com.helpline.helplineapi.data.contract.dashboard;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SummaryData {
    private long campaigns;

    private long volunteers;

    private long donations;

    private List<SummaryGraphicData> graphicData;
}
