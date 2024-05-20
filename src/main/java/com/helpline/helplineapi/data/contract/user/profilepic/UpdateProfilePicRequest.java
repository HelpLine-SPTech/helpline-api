package com.helpline.helplineapi.data.contract.user.profilepic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter @Setter
public class UpdateProfilePicRequest {
    private MultipartFile file;

    private UUID userId;
}
