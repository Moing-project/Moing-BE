package com.moing.moingbe.domain.image.service;

import com.moing.moingbe.global.dto.BaseResponseDto;
import com.moing.moingbe.global.enums.AccessCode;
import com.moing.moingbe.global.exception.GlobalStateException;
import com.moing.moingbe.global.image.ImageFolderEnum;
import com.moing.moingbe.global.image.S3ImageUploader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService {

    private final S3ImageUploader s3ImageUploader;

    public ImageService(S3ImageUploader s3ImageUploader) {
        this.s3ImageUploader = s3ImageUploader;
    }

    public BaseResponseDto<List<String>> uploadImage(List<MultipartFile> image) {
        if( image == null )
            throw GlobalStateException.builder("파일이 없습니다.").status(404).build();
        List<String> Filename = s3ImageUploader.storeImages(image, ImageFolderEnum.IMAGE);
        return BaseResponseDto.builder(Filename).status(AccessCode.IMAGE_CREATED.status()).msg(AccessCode.IMAGE_CREATED.code()).build();
    }

    public BaseResponseDto deleteImage(List<String> image) {
        return null;
    }
}
