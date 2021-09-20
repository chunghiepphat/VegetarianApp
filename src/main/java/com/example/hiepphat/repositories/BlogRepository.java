package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    public Blog findByBlogID(long id);
    List<Blog> findTop10ByTimeLessThanEqualOrderByTimeDesc(Date date);
}
