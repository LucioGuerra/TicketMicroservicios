package com.ticket.comment_sv.configuration;

import com.ticket.comment_sv.exception.TicketException;
import com.ticket.shared.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileServiceConfig {
    @Value("${MINIO_URL}")
    private String minioUrl;

    @Value("${MINIO_USER}")
    private String minioAccessKey;

    @Value("${MINIO_PASSWORD}")
    private String minioSecretKey;

    @Value("${MINIO_BUCKET}")
    private String minioBucketName;


    @Bean
    public FileService fileService(){
        return new FileService(minioUrl, minioAccessKey, minioSecretKey, minioBucketName, (code, message) -> {
            throw new TicketException(code, message);
        });
    }

}
