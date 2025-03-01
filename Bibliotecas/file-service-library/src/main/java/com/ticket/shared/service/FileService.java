package com.ticket.shared.service;

import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public class FileService {

    private final MinioClient minioClient;
    private final String bucketName;
    private final BiFunction<String, String, RuntimeException> ticketException;
    private final List<String> allowFiles;


    public FileService(String url, String accessKey, String secretKey, String bucketName,
                       BiFunction<String, String, RuntimeException> ticketException) {
        this.minioClient = MinioClient.builder()
                .endpoint("http://"+url)
                .credentials(accessKey, secretKey)
                .build();
        this.bucketName = bucketName;
        this.ticketException = ticketException;
        this.allowFiles = new ArrayList<>();
        allowFiles.add("application/pdf");
        allowFiles.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        allowFiles.add("application/msword");
        allowFiles.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        allowFiles.add("application/vnd.ms-excel");
    }

    @SneakyThrows
    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!allowFiles.contains(file.getContentType())) {
                throw ticketException.apply("INVALID_FILE_TYPE", "File type not allowed");
            }

            String sanitizeFileName = getSanitizeFileName(file);
            String uniqueSanitizedFileName = UUID.randomUUID().toString() + "_" + sanitizeFileName;
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(uniqueSanitizedFileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            fileNames.add(uniqueSanitizedFileName);
        }

        return fileNames;
    }

    @SneakyThrows
    public byte[] downloadFile(String fileName) {
        return minioClient.getObject(
                GetObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()).readAllBytes();
    }

    @SneakyThrows
    public void deleteFile( String fileName) {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }

    public String getContentType(String fileName) {
        try {
            return minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            ).contentType();
        } catch (Exception e) {
            throw ticketException.apply("FILE_NOT_FOUND", "File not found");
        }
    }

    private String getSanitizeFileName(MultipartFile file) {
        if (file.getOriginalFilename() == null) {
            throw new RuntimeException("File name is null");
        }
        return file.getOriginalFilename().replaceAll("[^a-zA-Z0-9.]", "_");
    }
}
