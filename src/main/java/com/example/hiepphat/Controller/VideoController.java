package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.User;
import com.example.hiepphat.Entity.Video;
import com.example.hiepphat.Entity.VideoCategory;
import com.example.hiepphat.dtos.VideoDTO;
import com.example.hiepphat.repositories.VideoRepository;
import com.example.hiepphat.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/video")
public class VideoController {
    @Autowired
    VideoRepository videoRepository;
    //chuc nang upload video cua user
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/upload")
    public ResponseEntity<?>uploadVideo(@RequestBody VideoDTO dto){
        Video video=new Video();
        User user=new User();
        user.setUserID(dto.getUser_id());
        VideoCategory videoCategory=new VideoCategory();
        videoCategory.setId(dto.getVideo_category_id());
        video.setUser(user);
        video.setVideoCategory(videoCategory);
        video.setTitle(dto.getVideo_title());
        video.setVideoLink(dto.getVideo_link());
        video.setDescription(dto.getVideo_description());
        videoRepository.save(video);
        return ResponseEntity.ok(new MessageResponse("Upload video successfully!!!"));
    }
}
