package com.helpline.helplineapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client() {

        AwsCredentials credentials = AwsBasicCredentials.create("dbe2711df45aed2f164f21541d308bdc", "c2ffb1f8fa14ce5e93200afa064abc79e967e2ce17c49c57b48876b0e1792df7");

        return S3Client.builder()
                .region(Region.SA_EAST_1)
                .endpointOverride(URI.create("https://nmcgdztcymerhtkgdots.supabase.co/storage/v1/s3"))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .forcePathStyle(true)
                .build();
    }
}