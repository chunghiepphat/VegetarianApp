package com.example.hiepphat.service;

import com.example.hiepphat.Entity.CommentBlog;
import com.example.hiepphat.repositories.CommentBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentBlogServiceImpl implements CommentBlogService {
    @Autowired
    CommentBlogRepository commentBlogRepository;

    @Override
    public CommentBlog save(CommentBlog commentBlog) {
        return commentBlogRepository.save(commentBlog);
    }
}
