package com.helpline.helplineapi.data.contract.user;

import com.helpline.helplineapi.data.contract.pix.PixInformationContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OngContract extends UserContract{
    public PixInformationContract pixInfo;
}
