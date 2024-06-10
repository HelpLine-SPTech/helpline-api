package com.helpline.helplineapi.data.contract.job.list;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ListJobRequest {

    private UUID ongId;

    private String desc;

    private String sort;

    private String order;

    private Integer page;

    private Integer size;
}
