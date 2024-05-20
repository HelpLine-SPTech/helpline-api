package com.helpline.helplineapi.services.user.profilepic;

import com.helpline.helplineapi.data.contract.user.profilepic.UpdateProfilePicRequest;
import com.helpline.helplineapi.data.contract.user.profilepic.UpdateProfilePicResponse;
import com.helpline.helplineapi.entities.file.FileEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.enums.FileTypeEnum;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.FileRepository;
import com.helpline.helplineapi.services.BaseService;
import com.helpline.helplineapi.storage.SupabaseStorage;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service responsible for uploading the user profile picture
 */
@Service
public class UpdateProfilePicService extends BaseService<UpdateProfilePicRequest, UpdateProfilePicResponse> {

    @Autowired
    private SupabaseStorage storage;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private BaseUserRepository userRepository;

    private BaseUserEntity user;

    private FileEntity fileEntity;

    @Override
    protected UpdateProfilePicResponse processService(UpdateProfilePicRequest request) {
        var response = new UpdateProfilePicResponse();

        if(fileRepository.existsByOwnerId(user.getId())) {
            this.fileEntity = fileRepository.findByOwnerIdAndType(this.user.getId(), FileTypeEnum.PROFILE_IMAGE).get();
        } else {
            this.fileEntity = new FileEntity();
            fileEntity.setOwner(user);
            fileEntity.setType(FileTypeEnum.PROFILE_IMAGE);
            fileRepository.save(fileEntity);
        }
        fileEntity.setOriginalFileName(request.getFile().getOriginalFilename());

        var url = this.uploadFile(fileEntity.getId().toString(), request, response);
        if(!response.getSuccess()) return response;
        fileEntity.setUrl(url);
        fileRepository.save(fileEntity);


        response.setUrl(fileEntity.getUrl());

        return response;
    }

    @Override
    protected UpdateProfilePicResponse validateService(UpdateProfilePicRequest request) {
        var response = new UpdateProfilePicResponse();

        var userOpt = userRepository.findById(request.getUserId());

        if(userOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
        } else {
            this.user = userOpt.get();
        }

        return response;
    }

    /**
     * Uploads the given file using the storage service
     * @param fileName name of the file that will be stored
     * @param request request containing the info needed to save the file
     * @param response response used to store errors in
     * @return The url of the saved file
     */
    private String uploadFile(String fileName, UpdateProfilePicRequest request, UpdateProfilePicResponse response) {
        try {
            String fileExtension = FilenameUtils.getExtension(request.getFile().getOriginalFilename());
            String filePath = String.format("profile/%s.%s", fileName, fileExtension);
            storage.uploadFile(filePath, request.getFile());
            return getFileUrl(String.format("%s.%s", fileName, fileExtension));
        } catch (IOException ex) {
            response.addError(ErrorCodeEnum.UNEXPECTED_ERROR);
            return "";
        }
    }

    /**
     * Generates the file url based on the filename
     * @param fileName the filename
     * @return Returns the generated full url
     */
    private String getFileUrl(String fileName) {
        return String.format("https://nmcgdztcymerhtkgdots.supabase.co/storage/v1/object/public/helpline-storage/profile/%s", fileName);
    }
}
