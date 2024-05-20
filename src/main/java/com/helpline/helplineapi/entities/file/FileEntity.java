package com.helpline.helplineapi.entities.file;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.FileTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "file")
public class FileEntity extends BaseEntity {

    private String originalFileName;

    private String url;

    @Enumerated(EnumType.STRING)
    private FileTypeEnum type;

    @OneToOne
    private BaseUserEntity owner;
}
