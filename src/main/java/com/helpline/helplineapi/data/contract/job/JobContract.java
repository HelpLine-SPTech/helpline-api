package com.helpline.helplineapi.data.contract.job;

import com.helpline.helplineapi.data.contract.address.AddressContract;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class JobContract {
    private UUID id;

    private String description;

    private List<String> abilities;

    private AddressContract address;

    private LocalDateTime date;

    private long amount;
}
