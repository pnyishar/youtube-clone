package com.pantech.youtubeclone.repository;

import com.pantech.youtubeclone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Paul Nyishar
 * @Date 24/04/2022
 */
public interface VideoRepository extends MongoRepository<Video, String> {
}
