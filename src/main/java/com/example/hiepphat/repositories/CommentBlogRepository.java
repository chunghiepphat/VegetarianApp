package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.CommentBlog;
import com.example.hiepphat.dtos.CommentBlogDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CommentBlogRepository extends JpaRepository<CommentBlog,Integer> {
    List<CommentBlog> findByBlog_BlogID(int id);
    CommentBlog findByBlog_BlogIDAndUser_UserIDAndTime(int blogID, int userID, Date date);
}
