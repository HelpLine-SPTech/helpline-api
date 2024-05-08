package com.helpline.helplineapi.services.dashboard;

import com.helpline.helplineapi.data.contract.dashboard.SummaryData;
import com.helpline.helplineapi.data.contract.dashboard.SummaryGraphicData;
import com.helpline.helplineapi.data.contract.dashboard.SummaryRequest;
import com.helpline.helplineapi.data.contract.dashboard.SummaryResponse;
import com.helpline.helplineapi.entities.donation.DonationEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.DonationRepository;
import com.helpline.helplineapi.repositories.OngRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SummaryService extends BaseService<SummaryRequest, SummaryResponse> {

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private DonationRepository donationRepository;

    private OngEntity ong;

    @Override
    protected SummaryResponse processService(SummaryRequest request) {
        var response = new SummaryResponse();
        var jobs = ong.getJobs();
        var summaryData = new SummaryData();
        summaryData.setCampaigns(jobs.size());
        summaryData.setVolunteers(jobs.stream().mapToLong(x -> x.getVolunteers().size()).sum());
        summaryData.setDonations(donationRepository.countByReceiverIdAndAddedAtAfter(request.getOngId(), LocalDateTime.of(
                LocalDate.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(), 0, 0).minusMonths(1)));

        summaryData.setGraphicData(getGraphicData(request));

        response.setSummary(summaryData);

        return response;
    }

    @Override
    protected SummaryResponse validateService(SummaryRequest request) {
        var response = new SummaryResponse();

        var ongOpt = ongRepository.findById(request.getOngId());

        if(ongOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
        } else ong = ongOpt.get();

        return response;
    }

    private List<SummaryGraphicData> getGraphicData(SummaryRequest request) {
        var data = new ArrayList<SummaryGraphicData>();
        int remainingDays = 31;
        var currentDate = LocalDateTime.of(
                LocalDate.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(), 0, 0);

        for (int i = 0; i < remainingDays; i++) {
            var startDate = currentDate;
            var endDate = currentDate.plusDays(1).minusNanos(1);

            var donations = donationRepository.findByReceiverIdAndAddedAtBetween(request.getOngId(), startDate, endDate);
            var sum = donations.stream().mapToLong(DonationEntity::getAmount).sum();

            var dataGroup = new SummaryGraphicData();
            dataGroup.setAmount(sum);
            dataGroup.setDate(currentDate);

            currentDate = currentDate.minusDays(1);

            data.add(dataGroup);
        }

        Collections.reverse(data);

        return data;
    }
}
