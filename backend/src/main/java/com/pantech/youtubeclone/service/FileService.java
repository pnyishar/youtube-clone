package com.pantech.youtubeclone.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Paul Nyishar
 * @Date 24/04/2022
 */

public interface FileService {
    String uploadFile(MultipartFile file);
}
