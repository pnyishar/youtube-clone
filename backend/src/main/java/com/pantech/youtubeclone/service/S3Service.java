package com.pantech.youtubeclone.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Paul Nyishar
 * @Date 24/04/2022
 */

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {
    public static final String BUCKET_NAME = "pantechtut";
    private final AmazonS3Client amazonS3Client;

    @Override
    public String uploadFile(MultipartFile file){

        var fileNameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        var key = UUID.randomUUID().toString() + "." + fileNameExtension;

        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try{
            amazonS3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
        }catch (IOException ioException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                              "An exception occurred while uploading the file");
        }
        amazonS3Client.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);
        return amazonS3Client.getResourceUrl(BUCKET_NAME, key);
    }
}
