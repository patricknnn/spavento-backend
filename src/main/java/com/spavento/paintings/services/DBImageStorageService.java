package com.spavento.paintings.services;

import com.spavento.paintings.exceptions.FileStorageException;
import com.spavento.paintings.exceptions.MyFileNotFoundException;
import com.spavento.paintings.models.DBImage;
import com.spavento.paintings.repository.DBImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DBImageStorageService {
    @Autowired
    private DBImageRepository dbImageRepository;

    public DBImage storeImage(Long paintingId, MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBImage dbImage = new DBImage(paintingId, fileName, file.getContentType(), file.getBytes());

            return dbImageRepository.save(dbImage);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store image " + fileName + ". Please try again!", ex);
        }
    }

    public DBImage getImage(Long imageId) {
        return dbImageRepository.findById(imageId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + imageId));
    }
}
