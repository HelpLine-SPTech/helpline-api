package com.helpline.helplineapi.services.donation;

import com.helpline.helplineapi.data.contract.donations.report.GetDonationReportRequest;
import com.helpline.helplineapi.data.contract.donations.report.GetDonationReportResponse;
import com.helpline.helplineapi.entities.donation.DonationEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.CampaignTypeEnum;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.repositories.DonationRepository;
import com.helpline.helplineapi.repositories.OngRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

@Service
public class DonationReportService extends BaseService<GetDonationReportRequest, GetDonationReportResponse> {

    private OngEntity ong;

    @Autowired
    private OngRepository ongRepository;


    @Override
    protected GetDonationReportResponse processService(GetDonationReportRequest request) {
        var response = new GetDonationReportResponse();

        var dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        var campaigns = ong.getCampaigns();

        var donations = campaigns
                .stream()
                .flatMap(campaignEntity -> campaignEntity.getDonations().stream())
                .filter(donation -> donation.getType() == CampaignTypeEnum.MONETARY)
                .toList();
        String fileName = String.format("DOACOES_%s_%s.csv", LocalDateTime.now().minusMonths(1).format(dateFormat), LocalDateTime.now().format(dateFormat));

        String csvString = getCsvString(donations);

        FileSystemResource fileResource;

        try {
            FileWriter file = new FileWriter(fileName);
            file.write(csvString);
            file.close();
            fileResource = new FileSystemResource(fileName);
            response.setCsv(fileResource);
        } catch (IOException ex) {
            response.addError(ErrorCodeEnum.UNEXPECTED_ERROR);
        }


        return response;
    }

    private String getCsvString(List<DonationEntity> donations) {
        var dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var sb = new StringBuilder();

        String[] headers = {
                "ID",
                "Valor",
                "Data",
                "Doador"
        };

        var headerJoiner = new StringJoiner(";");
        for (String header : headers) {
            headerJoiner.add(header);
        }

        sb.append(headerJoiner).append("\n");

        donations.forEach(donation -> {
            var sj = new StringJoiner(";");
            sj.add(donation.getId().toString());
            sj.add(String.format("R$%.2f", (double) donation.getAmount() / 100));
            sj.add(donation.getAddedAt().format(dateFormat));
            sj.add(donation.getDonor().getName());

            sb.append(sj).append("\n");
        });

        return sb.toString();
    }

    @Override
    protected GetDonationReportResponse validateService(GetDonationReportRequest request) {
        var response = new GetDonationReportResponse();

        var ongOpt = ongRepository.findById(request.getOngId());

        if(ongOpt.isEmpty()) response.addError(ErrorCodeEnum.OPERATION_NOT_PERMITTED);
        else ong = ongOpt.get();

        return response;
    }
}
