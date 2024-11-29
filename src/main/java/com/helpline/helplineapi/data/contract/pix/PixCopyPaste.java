package com.helpline.helplineapi.data.contract.pix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PixCopyPaste {
    private String code;

    private String qrCode;
}
