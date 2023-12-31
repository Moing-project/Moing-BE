package com.moing.moingbe.global.image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class S3ImageUploader {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public S3ImageUploader(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public List<String> storeImages(List<MultipartFile> multiPartFileList, ImageFolderEnum imageFolder) {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile multipartFile : multiPartFileList) {
            imageUrls.add(storeImage(multipartFile, imageFolder));
        }
        return imageUrls;
    }

    public String storeImage(MultipartFile multiPartFile, ImageFolderEnum imageFolder) {

        // 기존의 파일명
        String originalFileName = multiPartFile.getOriginalFilename();

        // 저장될 파일명
        String storeFileName = createStoreFileName(originalFileName, imageFolder.getFolderName());

        ObjectMetadata objectMetadata = createObjectMetadata(multiPartFile);
        try {
            InputStream inputStream = multiPartFile.getInputStream();
            amazonS3.putObject(new PutObjectRequest(bucket, storeFileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return amazonS3.getUrl(bucket, storeFileName).toString();

    }

    @Async
    public void deletePostImages(List<String> imageList) {
        for (String image : imageList) {
            deletePostImage(image);
        }
    }

    @Async
    public void deletePostImage(String image) {
        try {
            log.info("imageName = {}", image);
            String deleteFileName = createDeleteFileName(image);
            amazonS3.deleteObject(bucket, deleteFileName);
        } catch (RuntimeException e) {
            log.error("삭제 실패 파일명 = {}", image, e);
        }
    }

    private String createDeleteFileName(String image) {
        int startIndex = image.indexOf(".com/") + 5;
        String extractedString = image.substring(startIndex);
        String deleteFileName = URLDecoder.decode(extractedString, StandardCharsets.UTF_8).toString();
        log.info("extractedString = {}", deleteFileName);
        return deleteFileName;
    }

    private ObjectMetadata createObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        return objectMetadata;
    }

    private String createStoreFileName(String originalFileName, String folderName) {
        String ext = extractExt(originalFileName);
        String fileName = extractFileName(originalFileName);
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        String uuid = UUID.randomUUID().toString();
        log.info("fileName = {} ", fileName);
        return folderName + "/" + nowTime + fileName + uuid + "." + ext;
    }

    // 파일명의 확장자 파싱
    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(pos + 1);
        return ext;
    }

    private String extractFileName(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        String fileName = originalFileName.substring(0, pos);
        return fileName;
    }
}
