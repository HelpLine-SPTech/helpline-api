package com.helpline.helplineapi.data.contract.job;

import java.util.UUID;

public class ListJobRequest {

    private UUID ongId;
    private String sort;
    private String order;
    private Integer page;
    private Integer size;

    public UUID getOngId() {
        return ongId;
    }

    public void setOngId(UUID ongId) {
        this.ongId = ongId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
