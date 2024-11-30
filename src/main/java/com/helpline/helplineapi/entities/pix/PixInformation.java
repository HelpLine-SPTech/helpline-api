package com.helpline.helplineapi.entities.pix;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.PixKeyType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pixInfo")
@Table(name = "pixInfo")
public class PixInformation extends BaseEntity {
    private String key;

    private PixKeyType type;
}
