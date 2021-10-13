package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.User;
import com.example.hiepphat.Entity.Video;
import com.example.hiepphat.Entity.VideoCategory;
import com.example.hiepphat.dtos.BlogDTO;
import com.example.hiepphat.dtos.VideoDTO;
import com.example.hiepphat.repositories.VideoRepository;
import com.example.hiepphat.response.*;
import com.example.hiepphat.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/video")
public class VideoController {
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    VideoService videoService;
    //chuc nang upload video cua user
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/upload")
    public ResponseEntity<?>uploadVideo(@RequestBody VideoDTO dto) throws ParseException {
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
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String spf=simpleDateFormat.format(date);
        video.setTimeCreated(simpleDateFormat.parse(spf));
        videoRepository.save(video);
        return ResponseEntity.ok(new MessageResponse("Upload video successfully!!!"));
    }
    //chuc nang getall video
    @GetMapping("/getall")
    public VideoResponse showVideo(@RequestParam("page") int page, @RequestParam("limit") int limit){
       VideoResponse videoResponse=new VideoResponse();
        videoResponse.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit, Sort.by("timeCreated").descending());
        videoResponse.setListVideo(videoService.findAll(pageable));
        videoResponse.setTotalPage((int)Math.ceil((double)videoService.totalItem()/limit ));
        return videoResponse;
    }
    //chuc nang get 3 video moi nhat
    @GetMapping("/get4videos")
    public ThreeVideoResponse show10Blogs(){
        ThreeVideoResponse result=new ThreeVideoResponse();
        result.setListVideo(videoService.find3latestVideo());
        return result;
    }
    //chuc nang delete video theo video id
    @PreAuthorize("hasAuthority('user')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable("id")int id){
        Video video=videoRepository.findById(id);
        if(video!=null){
            videoRepository.delete(video);
            return ResponseEntity.ok(new MessageResponse("Delete video successfully!!!"));
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Nout found video id"));
        }
    }
    //chuc nang update video dua theo video id
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?>updateVideo(@RequestBody VideoDTO dto,@PathVariable("id")int id) throws ParseException {
        Video video=videoRepository.findById(id);
        if(video!=null){
             video.setTitle(dto.getVideo_title());
             video.setVideoLink(dto.getVideo_link());
             VideoCategory videoCategory=new VideoCategory();
             videoCategory.setId(dto.getVideo_category_id());
             video.setVideoCategory(videoCategory);
             video.setDescription(dto.getVideo_description());
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();
            String spf=simpleDateFormat.format(date);
             video.setTimeUpdated(simpleDateFormat.parse(spf));
             videoRepository.save(video);
            return ResponseEntity.ok(new MessageResponse("Update video successfully!!!"));
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Nout found video id"));
        }
    }
    //chuc nang get video detail theo id
    @GetMapping("/getvideoby/{id}")
    public VideoDTO showVideobyID(@PathVariable int id) throws Exception {
        VideoDTO result=videoService.findById(id);
        if(result==null){
            throw new Exception("Nout found blog id:"+ id);
        }
        return result;
    }
}
