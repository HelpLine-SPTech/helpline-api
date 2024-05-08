package com.helpline.helplineapi;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(scheme = "bearer", name = "helpline-api", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class HelplineApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelplineApiApplication.class, args);
    }

}
