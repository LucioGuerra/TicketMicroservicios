package com.tickets.requirement_sv.service;

import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FileService {

    private final MinioClient minioClient;

    public void uploadFile(String bucketName, String fileName, String filePath) {
        try {
            minioClient.uploadObject(bucketName, fileName, filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to Minio", e);
        }
    }

    public void deleteFile(String bucketName, String fileName) {
        try {
            minioClient.removeObject(bucketName, fileName);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting file from Minio", e);
        }
    }

    public void downloadFile(String bucketName, String fileName, String filePath) {
        try {
            minioClient.downloadObject(bucketName, fileName, filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error downloading file from Minio", e);
        }
    }
}
