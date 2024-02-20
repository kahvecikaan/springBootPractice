package com.project.rentACar.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String storeImage(MultipartFile image);
}
