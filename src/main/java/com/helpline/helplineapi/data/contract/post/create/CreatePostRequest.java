package com.helpline.helplineapi.data.contract.post.create;

import com.helpline.helplineapi.data.contract.post.PostContract;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class CreatePostRequest {
    private String content;

    private List<MultipartFile> images;

    private UUID userId;
}
