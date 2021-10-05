package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.LikeBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeBlogRepository extends JpaRepository<LikeBlog,Long> {
    LikeBlog findByUser_UserIDAndBlog_BlogID(int user_id,long blog_id);
    List<LikeBlog> findByBlog_BlogID(int id);
}
