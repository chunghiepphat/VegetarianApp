package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    List<Video>findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String title,String fn,String ln);
}
