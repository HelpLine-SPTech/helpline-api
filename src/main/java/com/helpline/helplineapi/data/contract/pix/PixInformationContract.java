package com.helpline.helplineapi.data.contract.pix;

import com.helpline.helplineapi.enums.PixKeyType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PixInformationContract {
    private String key;

    private PixKeyType type;
}
