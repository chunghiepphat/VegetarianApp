package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.TenBlogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    public Blog findByBlogID(long id);
    List<Blog> findTop10ByTimeLessThanEqualOrderByTimeDesc(Date date);
    List<Blog> findTop10ByUser_UserIDOrderByTimeDesc(int userID);
    List<Blog> findAllByUser_UserID(Pageable pageable, int userID);
    int countByUser_UserID(int userID);
}
