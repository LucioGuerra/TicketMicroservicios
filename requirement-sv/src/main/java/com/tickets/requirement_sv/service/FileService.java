package com.tickets.requirement_sv.service;

import com.tickets.requirement_sv.exception.TicketException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    private final MinioClient minioClient;

    @Value("${MINIO_BUCKET}")
    private String bucketName;

    private final List<String> allowFiles = List.of(
            MediaType.APPLICATION_PDF_VALUE,
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-excel"
    );

    @SneakyThrows
    public List<String> uploadFile(List<MultipartFile> files) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!allowFiles.contains(file.getContentType())) {
                throw new TicketException("INVALID_FILE_TYPE", "File type not allowed");
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
    public String downloadFile(String fileName) {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(fileName)
                        .expiry(60 * 60)
                        .build()
        );
    }

    @SneakyThrows
    public void deleteFile(String bucketName, String fileName) {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }

    private String getSanitizeFileName(MultipartFile file) {
        if (file.getOriginalFilename() == null) {
            throw new RuntimeException("File name is null");
        }
        return file.getOriginalFilename().replaceAll("[^a-zA-Z0-9.]", "_");
    }
}
