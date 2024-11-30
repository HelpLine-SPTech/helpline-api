package com.helpline.helplineapi.services.donation;

import com.helpline.helplineapi.data.contract.donations.DonationContract;
import com.helpline.helplineapi.data.contract.donations.confirm.ConfirmDonationRequest;
import com.helpline.helplineapi.data.contract.donations.create.CreateDonationRequest;
import com.helpline.helplineapi.data.contract.donations.create.CreateDonationResponse;
import com.helpline.helplineapi.data.contract.pix.PixCopyPaste;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.donation.DonationEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.CampaignTypeEnum;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.DonationMapper;
import com.helpline.helplineapi.pix.DadosEnvioPix;
import com.helpline.helplineapi.pix.QRCodePix;
import com.helpline.helplineapi.repositories.CampaignRepository;
import com.helpline.helplineapi.repositories.DonationRepository;
import com.helpline.helplineapi.repositories.OngRepository;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import com.helpline.helplineapi.services.campaign.CreateCampaignService;
import com.helpline.helplineapi.storage.SupabaseStorage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CreateDonationService extends BaseService<CreateDonationRequest, CreateDonationResponse> {

    private CampaignEntity campaign;

    private final CampaignRepository campaignRepository;

    private final DonationRepository donationRepository;

    private final SupabaseStorage storage;

    private final ConfirmDonationService confirmDonationService;

    public CreateDonationService(CampaignRepository campaignRepository, DonationRepository donationRepository, SupabaseStorage storage, ConfirmDonationService confirmDonationService) {
        this.campaignRepository = campaignRepository;
        this.donationRepository = donationRepository;
        this.storage = storage;
        this.confirmDonationService = confirmDonationService;
    }

    @Override
    protected CreateDonationResponse processService(CreateDonationRequest request) {
        var response = new CreateDonationResponse();

        var newDonation = this.getDonationResult(request);
        if (newDonation.getType() == CampaignTypeEnum.MONETARY) {
            var pixCopyAndPaste = generatePixCopyAndPaste(request);

            var confirmRequest = new ConfirmDonationRequest();
            confirmRequest.setRequesterUserId(request.getDonor().getId());
            confirmRequest.setDonationId(request.getDonation().getId());

            confirmDonationService.process(confirmRequest);
            
            response.setPix(pixCopyAndPaste);
        }

        var inserted = donationRepository.save(newDonation);
        response.setDonation(DonationMapper.toContract(inserted));

        return response;
    }

    @Override
    protected CreateDonationResponse validateService(CreateDonationRequest request) {
        var response = new CreateDonationResponse();

        campaignRepository.findById(request.getDonation().getCampaignId())
                .ifPresentOrElse(
                        campaignEntity -> campaign = campaignEntity,
                        () -> response.addError(ErrorCodeEnum.NOT_FOUND_ERROR));

        return response;
    }

    private DonationEntity getDonationResult(CreateDonationRequest request) {
        var contract = request.getDonation();

        var entity = new DonationEntity();
        entity.setAmount(contract.getAmount());
        entity.setType(campaign.getType());
        entity.setCampaign(campaign);
        entity.setDonor(request.getDonor());
        entity.setQuantity(contract.getQuantity());

        return entity;
    }

    private PixCopyPaste generatePixCopyAndPaste(CreateDonationRequest request) {
        var ong = campaign.getOng();
        var pixData = new DadosEnvioPix(
                ong.getName().length() > 25 ? ong.getName().substring(0, 24) : ong.getName(),
                ong.getPixInfo().getKey(),
                new BigDecimal(request.getDonation().getAmount() / 100),
                ong.getAddress() == null ? "Sao Paulo" : ong.getAddress().getCity()
        );

        var qrCodeGenerator = new QRCodePix(pixData);

        try {
            var qrCodeInfo = qrCodeGenerator.save();
            var fileName = "%s_%s.png".formatted(request.getDonor().getId(), LocalDateTime.now());
            storage.uploadFile("pix/qrcode/%s".formatted(fileName), qrCodeInfo.qrCode);
            var url = getFileUrl(fileName);
            return new PixCopyPaste(qrCodeInfo.code, url);
        } catch (IOException ex){
            Logger.getGlobal().log(Level.SEVERE, "Falha ao salvar imagem do qr code");
        }

        return null;
    }

    /**
     * Generates the file url based on the filename
     * @param fileName the filename
     * @return Returns the generated full url
     */
    private String getFileUrl(String fileName) {
        return String.format("https://nmcgdztcymerhtkgdots.supabase.co/storage/v1/object/public/helpline-storage/pix/qrcode/%s", fileName);
    }
}
