package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Video;
import com.example.hiepphat.dtos.TenBlogDTO;
import com.example.hiepphat.dtos.VideoDTO;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface VideoService {
    List<VideoDTO> findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String title,String fn,String ln);
    List<VideoDTO>findAll(Pageable pageable);
    int totalItem();
    List<VideoDTO>find3latestVideo();
    VideoDTO findById(int id);
}
