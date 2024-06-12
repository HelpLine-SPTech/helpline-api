package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.file.FileContract;
import com.helpline.helplineapi.entities.file.FileEntity;

import java.util.List;

public abstract class FileMapper {
    public static FileContract toDto(FileEntity self) {
        if(self == null) {
            return null;
        }

        var dto = new FileContract();
        dto.setUrl(self.getUrl());

        return dto;
    }

    public static List<FileContract> toDto(List<FileEntity> self) {
        if(self == null) return null;
        return self.stream().map(FileMapper::toDto).toList();
    }
}
