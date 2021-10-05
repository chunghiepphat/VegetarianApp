package com.example.hiepphat.service;

import com.example.hiepphat.Entity.CommentBlog;
import com.example.hiepphat.dtos.CommentBlogDTO;
import com.example.hiepphat.repositories.CommentBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class CommentBlogServiceImpl implements CommentBlogService {
    @Autowired
    CommentBlogRepository commentBlogRepository;

    @Override
    public CommentBlog save(CommentBlog commentBlog) {
        return commentBlogRepository.save(commentBlog);
    }

    @Override
    public List<CommentBlogDTO> findByBlog_BlogID(int id) {
        List<CommentBlogDTO>result=new ArrayList<>();
        List<CommentBlog>entities=commentBlogRepository.findByBlog_BlogID(id);
        for(CommentBlog item:entities){
            CommentBlogDTO dto=new CommentBlogDTO();
            dto.setFirst_name(item.getUser().getFirstName());
            dto.setLast_name(item.getUser().getLastName());
            dto.setBlog_id(item.getBlog().getBlogID());
            dto.setUser_id(item.getUser().getUserID());
            dto.setContent(item.getContent());
            dto.setTime(item.getTime());
            dto.setId(item.getId());
            result.add(dto);
        }
        return result;
    }

    @Override
    public void deleteUserCommentByID(int id) {
        commentBlogRepository.deleteById(id);
    }

    @Override
    public void deleteCommentBlog(int id) {
        List<CommentBlog>entites=commentBlogRepository.findByBlog_BlogID(id);
        for(CommentBlog item:entites){
            commentBlogRepository.delete(item);
        }
    }
}
