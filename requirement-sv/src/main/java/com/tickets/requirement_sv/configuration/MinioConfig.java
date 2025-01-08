package com.tickets.requirement_sv.configuration;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${MINIO_URL}")
    private String url;

    @Value("${MINIO_USER}")
    private String accessKey;

    @Value("${MINIO_PASSWORD}")
    private String secretKey;

    @Bean
    public MinioClient minioClient(){

        return MinioClient.builder()
                .endpoint("http://"+url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
