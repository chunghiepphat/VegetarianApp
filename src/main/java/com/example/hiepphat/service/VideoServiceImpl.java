package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Video;
import com.example.hiepphat.dtos.VideoDTO;
import com.example.hiepphat.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
            dto.setId(item.getId());
            dto.setUser_id(item.getUser().getUserID());
            dto.setVideo_link(item.getVideoLink());
            dto.setVideo_category_id(item.getVideoCategory().getId());
            dto.setVideo_title(item.getTitle());
            dto.setVideo_description(item.getDescription());
            dto.setTime_created(item.getTimeCreated());
            dto.setFirst_name(item.getUser().getFirstName());
            dto.setLast_name(item.getUser().getLastName());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<VideoDTO> findAll(Pageable pageable) {
        List<VideoDTO>result=new ArrayList<>();
        List<Video>entity=videoRepository.findAll(pageable).getContent();
        for(Video item:entity){
            VideoDTO dto=new VideoDTO();
            dto.setId(item.getId());
            dto.setVideo_title(item.getTitle());
            dto.setVideo_description(item.getDescription());
            dto.setVideo_link(item.getVideoLink());
            dto.setUser_id(item.getUser().getUserID());
            dto.setVideo_category_id(item.getVideoCategory().getId());
            dto.setFirst_name(item.getUser().getFirstName());
            dto.setLast_name(item.getUser().getLastName());
            dto.setTime_created(item.getTimeCreated());
            result.add(dto);
        }
        return result;
    }

    @Override
    public int totalItem() {
        return (int)videoRepository.count();
    }

    @Override
    public List<VideoDTO> find3latestVideo() {
        List<VideoDTO>result=new ArrayList<>();
        Date date=new Date();
        List<Video>entity=videoRepository.findTop4ByTimeCreatedLessThanEqualOrderByTimeCreatedDesc(date);
        for(Video item:entity){
            VideoDTO dto=new VideoDTO();
            dto.setId(item.getId());
            dto.setVideo_link(item.getVideoLink());
            dto.setVideo_category_id(item.getVideoCategory().getId());
            dto.setVideo_description(item.getDescription());
            dto.setVideo_title(item.getTitle());
            dto.setUser_id(item.getUser().getUserID());
            dto.setFirst_name(item.getUser().getFirstName());
            dto.setLast_name(item.getUser().getLastName());
            dto.setTime_created(item.getTimeCreated());
            result.add(dto);
        }
        return result;
    }

    @Override
    public VideoDTO findById(int id) throws Exception {
        Video video=videoRepository.findById(id);
        VideoDTO dto=new VideoDTO();
        if(video!=null){
            dto.setUser_id(video.getUser().getUserID());
            dto.setVideo_title(video.getTitle());
            dto.setId(video.getId());
            dto.setVideo_link(video.getVideoLink());
            dto.setVideo_description(video.getDescription());
            dto.setVideo_category_id(video.getVideoCategory().getId());
            dto.setLast_name(video.getUser().getLastName());
            dto.setFirst_name(video.getUser().getFirstName());
            dto.setTime_created(video.getTimeCreated());
        }
       else{
           throw new Exception("Video not found");
        }
        return dto;
    }
}
