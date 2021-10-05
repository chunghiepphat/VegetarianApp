package com.example.hiepphat.service;

import com.example.hiepphat.Entity.CommentBlog;
import com.example.hiepphat.dtos.CommentBlogDTO;

import java.util.List;

public interface CommentBlogService {
    CommentBlog save(CommentBlog commentBlog);
    List<CommentBlogDTO> findByBlog_BlogID(int id);
    void deleteUserCommentByID(int id);
    void deleteCommentBlog(int id);
}
