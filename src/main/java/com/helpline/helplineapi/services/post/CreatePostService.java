package com.helpline.helplineapi.services.post;

import com.helpline.helplineapi.data.contract.post.create.CreatePostRequest;
import com.helpline.helplineapi.data.contract.post.create.CreatePostResponse;
import com.helpline.helplineapi.entities.file.FileEntity;
import com.helpline.helplineapi.entities.post.PostEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.enums.FileTypeEnum;
import com.helpline.helplineapi.mappers.PostMapper;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.FileRepository;
import com.helpline.helplineapi.repositories.PostRepository;
import com.helpline.helplineapi.services.BaseService;
import com.helpline.helplineapi.storage.SupabaseStorage;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreatePostService extends BaseService<CreatePostRequest, CreatePostResponse> {

    private final String BASE_STORAGE_POST_IMAGE_URL = "https://nmcgdztcymerhtkgdots.supabase.co/storage/v1/object/public/helpline-storage/post/%s/%s";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BaseUserRepository userRepository;

    @Autowired
    private SupabaseStorage storage;

    @Autowired
    private FileRepository fileRepository;

    private BaseUserEntity user;

    @Override
    protected CreatePostResponse processService(CreatePostRequest request) {
        var response = new CreatePostResponse();
        var entity = new PostEntity();
        entity.setContent(request.getContent());
        entity.setUser(user);

        var savedPost = postRepository.save(entity);
        var imagesEntities = this.uploadImages(request.getImages(), savedPost.getId(), response);
        savedPost.setImages(imagesEntities);
        savedPost = postRepository.save(savedPost);
        if(!response.getSuccess()) return response;

        response.setPost(PostMapper.toDto(savedPost, UUID.randomUUID()));

        return response;
    }

    @Override
    protected CreatePostResponse validateService(CreatePostRequest request) {
        var response = new CreatePostResponse();
        var userOpt = userRepository.findById(request.getUserId());

        if(userOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }

        this.user = userOpt.get();

        return response;
    }

    private List<FileEntity> uploadImages(List<MultipartFile> images, UUID postId, CreatePostResponse response) {
        List<FileEntity> files = new ArrayList<>();

        images.forEach(image -> {
            var fileEntity = new FileEntity();
            var url = uploadImage(image, postId, response);
            fileEntity.setType(FileTypeEnum.POST_IMAGE);
            fileEntity.setUrl(url);
            fileEntity.setOriginalFileName(image.getOriginalFilename());
            var savedFile = fileRepository.save(fileEntity);
            files.add(savedFile);
        });

        return files;
    }

    private String uploadImage(MultipartFile image, UUID postId, CreatePostResponse response) {
        try {
            String filePath = String.format("post/%s/%s", postId, image.getOriginalFilename());
            storage.uploadFile(filePath, image);
            return getFileUrl(postId, image.getOriginalFilename());
        } catch (IOException ex) {
            response.addError(ErrorCodeEnum.UNEXPECTED_ERROR);
            return "";
        }
    }

    private String getFileUrl(UUID postId, String fileName) {
        return String.format(BASE_STORAGE_POST_IMAGE_URL, postId, fileName);
    }
}
