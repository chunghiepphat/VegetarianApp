package com.example.hiepphat.service;

import com.example.hiepphat.Entity.LikeBlog;
import com.example.hiepphat.dtos.ListLikeDTO;

import java.util.List;

public interface LikeBlogService {
    LikeBlog findByUser_UserIDAndBlog_BlogID(int user_id, int blog_id);
    void deleteLike(int id);
    List<ListLikeDTO> viewListUserLike(int id);
}
