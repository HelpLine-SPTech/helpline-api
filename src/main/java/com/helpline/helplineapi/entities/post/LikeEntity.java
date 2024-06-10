package com.helpline.helplineapi.entities.post;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_like")
@Getter @Setter
public class LikeEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn
    private BaseUserEntity user;

    @ManyToOne
    @JoinColumn
    private PostEntity post;
}
