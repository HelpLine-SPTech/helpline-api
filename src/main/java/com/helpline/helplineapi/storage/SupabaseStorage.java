package com.helpline.helplineapi.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;

/**
 * Supabase storage service
 */
@Service
public class SupabaseStorage {

    /**
     * AWS S3 client used to interact with the bucket
     */
    private final S3Client s3Client;

    /**
     * The helpline bucket name, stored in application.properties
     */
    private final String bucketName;

    /**
     * Default constructor
     */
    public SupabaseStorage(S3Client s3Client, @Value("${aws.s3.bucket-name}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    /**
     * Uploads a file to the helpline-bucket
     * @param key the file path
     * @param file the file
     * @return true if uploaded successfully
     */
    public String uploadFile(String key, MultipartFile file) throws IOException {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        try (var inputStream = file.getInputStream()) {
            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));

            if (response.sdkHttpResponse().isSuccessful()) {
                return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(key)).toExternalForm();
            } else {
                throw new IOException("Failed to upload file to S3");
            }
        }
    }

    /**
     * Deletes the file at the given path
     * @param key the path
     * @return true if deleted successfully
     */
    public boolean deleteFile(String key) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        var response = s3Client.deleteObject(request);

        return response.sdkHttpResponse().isSuccessful();
    }

}
