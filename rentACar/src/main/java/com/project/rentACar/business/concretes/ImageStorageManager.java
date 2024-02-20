package com.project.rentACar.business.concretes;

import com.project.rentACar.business.abstracts.ImageStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageManager implements ImageStorageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageStorageManager.class);

    @Value("${app.image.storage-directory}")
    private String storageDirectoryPath;

    @Override
    public String storeImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new IllegalStateException("Image is empty");
        }

        try {
            String fileExtension = getFileExtension(image.getOriginalFilename());
            String fileName = UUID.randomUUID() + (fileExtension.isEmpty() ? "" : "." + fileExtension);
            Path storageDirectory = Paths.get(storageDirectoryPath);

            if (!Files.exists(storageDirectory)) {
                Files.createDirectories(storageDirectory);
            }

            Path destinationPath = storageDirectory.resolve(fileName);
            image.transferTo(destinationPath);

            // Return the relative path to be stored in the database
            return Paths.get("images/cars", fileName).toString();
        } catch (IOException e) {
            logger.error("Failed to store image: {}", e.getMessage());
            throw new IllegalStateException("Failed to store image: " + e.getMessage(), e);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
