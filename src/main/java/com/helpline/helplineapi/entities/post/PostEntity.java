package com.helpline.helplineapi.entities.post;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.file.FileEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "post")
@Getter @Setter
public class PostEntity extends BaseEntity {

    private String content;

    @OneToMany(mappedBy = "post")
    private List<LikeEntity> likes;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments;

    @OneToMany
    private List<FileEntity> images;

    @ManyToOne
    @JoinColumn
    private BaseUserEntity user;

}
