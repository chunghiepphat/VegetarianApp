package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Video;
import com.example.hiepphat.dtos.VideoDTO;

import java.util.List;

public interface VideoService {
    List<VideoDTO> findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String title,String fn,String ln);
}
