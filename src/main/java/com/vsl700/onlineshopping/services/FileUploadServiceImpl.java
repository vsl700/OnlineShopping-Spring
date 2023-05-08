package com.vsl700.onlineshopping.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final Path root = Paths.get("uploads");
    private final Random fileNameRandom = new Random();

    public FileUploadServiceImpl(){
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String save(MultipartFile file) {
        try {
            String fileName = "%s_%s".formatted(fileNameRandom.nextInt(), file.getOriginalFilename());
            Files.copy(file.getInputStream(), root.resolve(fileName));

            return "/%s".formatted(fileName);
        } catch (IOException e) { // TODO I think that if there is a problem with uploading the file, the user should be notified!
            e.printStackTrace();
            return "#";
        }
    }
    
}
