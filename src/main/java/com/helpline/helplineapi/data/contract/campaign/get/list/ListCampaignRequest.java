package com.helpline.helplineapi.data.contract.campaign.get.list;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ListCampaignRequest {
    private UUID ongId;

    private String desc;

    private String sort;

    private String order;

    private Integer page;

    private Integer size;
}
