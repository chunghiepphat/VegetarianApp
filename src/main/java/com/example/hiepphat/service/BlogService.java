package com.example.hiepphat.service;

import com.example.hiepphat.dtos.BlogDTO;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.dtos.TenBlogDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    List<TenBlogDTO>findAll(Pageable pageable);
    int totalItem();
    BlogDTO findblogbyID(long id);
    List<TenBlogDTO>findTop10Records();
}
