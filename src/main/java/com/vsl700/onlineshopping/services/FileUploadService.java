package com.vsl700.onlineshopping.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    /**
     * 
     * @param file
     * @return a link to the uploaded file
     */
    String save(MultipartFile file);
}
