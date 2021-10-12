package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Video;
import com.example.hiepphat.dtos.VideoDTO;
import com.example.hiepphat.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<VideoDTO> findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String title, String fn, String ln) {
        List<VideoDTO>result=new ArrayList<>();
        List<Video>entity=videoRepository.findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+title+"%","%"+fn+"%","%"+ln+"%");
        for(Video item:entity){
            VideoDTO dto=new VideoDTO();
            dto.setUser_id(item.getUser().getUserID());
            dto.setVideo_link(item.getVideoLink());
            dto.setVideo_category_id(item.getVideoCategory().getId());
            dto.setVideo_title(item.getTitle());
            result.add(dto);
        }
        return result;
    }
}
