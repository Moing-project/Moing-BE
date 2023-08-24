package com.moing.moingbe.domain.image.controller;


import com.moing.moingbe.domain.image.service.ImageService;
import com.moing.moingbe.global.dto.BaseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/image")
    public ResponseEntity<BaseResponseDto<List<String>>> uploadImage(@RequestPart("imageFile") List<MultipartFile> image){
        return ResponseEntity.ok(imageService.uploadImage(image));
    }

    @DeleteMapping("/image")
    public ResponseEntity<BaseResponseDto> deleteImage(List<String> image){
        return ResponseEntity.ok(imageService.deleteImage(image));
    }
}
