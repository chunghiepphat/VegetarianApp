package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.BlogDTO;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.dtos.TenBlogDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface BlogService {
    List<TenBlogDTO>findAll(Pageable pageable);
    int totalItem();
    BlogDTO findblogbyID(long id);
    List<TenBlogDTO>findTop10Records();
    List<TenBlogDTO> findTop10ByUser_UserIDOrderByTimeDesc(int userID);
    List<TenBlogDTO> findAllByUser_UserID(Pageable pageable, int userID);
    int countByUser_UserID(int userID);
    public Blog save(Blog blog);
    List<TenBlogDTO>findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String title,String fn,String ln);
    List<TenBlogDTO>findBestBlog();
    List<TenBlogDTO>findLikedBlog(int id);
}
