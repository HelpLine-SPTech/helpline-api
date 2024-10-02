package com.helpline.helplineapi.entities.badge;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.enums.BadgeLevelEnum;
import com.helpline.helplineapi.enums.BadgeTypeEnum;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BadgeEntity extends BaseEntity {
    private BadgeTypeEnum type;

    private BadgeLevelEnum level;
}
