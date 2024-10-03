package com.helpline.helplineapi.entities.user;

import com.helpline.helplineapi.entities.badge.BadgeEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class UserEntity extends BaseUserEntity{
    @ElementCollection
    private List<String> abilities;

    @OneToMany
    private List<BadgeEntity> badges;
}
