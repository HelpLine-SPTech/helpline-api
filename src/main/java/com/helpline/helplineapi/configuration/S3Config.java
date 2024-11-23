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

        AwsCredentials credentials = AwsBasicCredentials.create("9f7aa1aa5cefa381cb70446bc839407e", "c6acc6756f8a878d33f9f84b77cf81d6cd04bcf705d6053b29266a287c6de072");

        return S3Client.builder()
                .region(Region.SA_EAST_1)
                .endpointOverride(URI.create("https://nmcgdztcymerhtkgdots.supabase.co/storage/v1/s3"))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .forcePathStyle(true)
                .build();
    }
}