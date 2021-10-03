package com.example.hiepphat.service;

import com.example.hiepphat.Entity.LikeBlog;

public interface LikeBlogService {
    LikeBlog findByUser_UserIDAndBlog_BlogID(int user_id, long blog_id);
}
