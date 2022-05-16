package com.pantech.youtubeclone.service;

import com.pantech.youtubeclone.model.Video;
import com.pantech.youtubeclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Paul Nyishar
 * @Date 24/04/2022
 */
@Service
@RequiredArgsConstructor
public class VideoService {
    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile multipartFile){
       String videoUrl = s3Service.uploadFile(multipartFile);
       var video = new Video();
       video.setVideoUrl(videoUrl);

       videoRepository.save(video);
    }
}
