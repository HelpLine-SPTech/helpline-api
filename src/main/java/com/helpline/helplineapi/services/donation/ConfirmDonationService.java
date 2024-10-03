package com.helpline.helplineapi.services.donation;

import com.helpline.helplineapi.data.contract.donations.confirm.ConfirmDonationRequest;
import com.helpline.helplineapi.data.contract.donations.confirm.ConfirmDonationResponse;
import com.helpline.helplineapi.entities.badge.BadgeEntity;
import com.helpline.helplineapi.entities.donation.DonationEntity;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.BadgeLevelEnum;
import com.helpline.helplineapi.enums.BadgeTypeEnum;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.BadgeMapper;
import com.helpline.helplineapi.repositories.BadgeRepository;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.DonationRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmDonationService extends BaseService<ConfirmDonationRequest, ConfirmDonationResponse> {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private BaseUserRepository userRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    private DonationEntity donationEntity;

    @Override
    protected ConfirmDonationResponse processService(ConfirmDonationRequest request) {

        donationEntity.setConfirmed(true);
        donationRepository.save(donationEntity);

        updateDonorBadge();

        return new ConfirmDonationResponse();
    }

    @Override
    protected ConfirmDonationResponse validateService(ConfirmDonationRequest request) {
        var response = new ConfirmDonationResponse();

        var donation = donationRepository.findById(request.getDonationId());

        if(donation.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        } else {
            donationEntity = donation.get();
        }

        var requesterUser = userRepository.findById(request.getRequesterUserId());

        if(requesterUser.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        } else {
            var user = requesterUser.get();

            if(user.getId() != donationEntity.getCampaign().getOng().getId()) {
                response.addError(ErrorCodeEnum.OPERATION_NOT_PERMITTED);
                return response;
            }
        }

        return response;
    }

    private void updateDonorBadge() {
        var donor = donationEntity.getDonor();
        var badgeOpt = donor.getBadges().stream().filter(b -> b.getType() == donationEntity.getCampaign().getBadgeType()).findFirst();

        if(badgeOpt.isEmpty()) {
            newBadgeFlow(donor, donationEntity.getCampaign().getBadgeType());
        } else {
            existingBadgeFlow(badgeOpt.get());
        }
    }

    private void newBadgeFlow(UserEntity user, BadgeTypeEnum badgeType) {
        var newBadge = new BadgeEntity();
        newBadge.setLevel(BadgeLevelEnum.ZERO_STAR);
        newBadge.setType(badgeType);

        user.getBadges().add(newBadge);
        userRepository.save(user);
    }

    private void existingBadgeFlow(BadgeEntity badge) {
        var allDonorDonations = donationRepository.findByDonorId(donationEntity.getDonor().getId());

        int newTotalAmount = allDonorDonations
                .stream()
                .filter(d -> d.getType() == donationEntity.getType())
                .mapToInt(DonationEntity::getQuantity)
                .sum();

        badge.setLevel(BadgeMapper.getNextLevel(newTotalAmount, badge.getType()));
        badgeRepository.save(badge);

    }
}
