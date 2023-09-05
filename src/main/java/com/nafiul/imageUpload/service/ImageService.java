package com.nafiul.imageUpload.service;

import com.nafiul.imageUpload.entity.ImageFile;
import com.nafiul.imageUpload.repository.ImageRepository;
import com.nafiul.imageUpload.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(MultipartFile imageFile) throws IOException {
        ImageFile imageData = ImageFile.builder()
                .fileName(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtils.compressImage(imageFile.getBytes()))
                .build();
        if(Objects.nonNull(imageData)){
            imageRepository.save(imageData);
            return "Image upload Successfully";
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageFile> image = imageRepository.findByName(fileName);
        return ImageUtils.decompressImage(image.get().getImageData());
    }

}
