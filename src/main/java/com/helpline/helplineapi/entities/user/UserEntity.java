package com.helpline.helplineapi.entities.user;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class UserEntity extends BaseUserEntity{
    @ElementCollection
    private List<String> abilities;
}
