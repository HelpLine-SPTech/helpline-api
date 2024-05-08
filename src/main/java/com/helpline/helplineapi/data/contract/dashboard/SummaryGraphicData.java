package com.helpline.helplineapi.data.contract.dashboard;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class SummaryGraphicData {
    private long amount;

    private LocalDateTime date;
}
