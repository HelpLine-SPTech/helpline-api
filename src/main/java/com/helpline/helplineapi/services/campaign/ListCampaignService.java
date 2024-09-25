package com.helpline.helplineapi.services.campaign;

import java.util.List;
import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import com.helpline.helplineapi.data.contract.campaign.get.list.ListCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.get.list.ListCampaignResponse;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.mappers.CampaignMapper;
import com.helpline.helplineapi.repositories.CampaignRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListCampaignService extends BaseService<ListCampaignRequest, ListCampaignResponse> {

    @Autowired
    private CampaignRepository repository;

    @Override
    protected ListCampaignResponse processService(ListCampaignRequest request) {
        Sort.Direction direction = request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(direction, request.getSort()));
        Page<CampaignEntity> campaignEntities = repository.findAllByOngId(request.getOngId(), pageable);


        List<CampaignContract> campaignContracts = CampaignMapper.toContract(campaignEntities.getContent());
        if(!request.getDesc().isEmpty()) {
            campaignContracts = campaignContracts
                    .stream()
                    .filter(j -> j.getDescription().toLowerCase().contains(request.getDesc().toLowerCase()) || j.getTitle().toLowerCase().contains(request.getDesc().toLowerCase()))
                    .toList();
        }

        var response = new ListCampaignResponse();
        response.setCampaigns(campaignContracts);
        response.setTotalPages(campaignEntities.getTotalPages());
        response.setTotalElements(campaignEntities.getTotalElements());
        return response;
    }

    @Override
    protected ListCampaignResponse validateService(ListCampaignRequest listCampaignRequest) {
        return new ListCampaignResponse();
    }
}
