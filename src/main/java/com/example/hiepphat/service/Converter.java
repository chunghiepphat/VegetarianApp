package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.BlogDTO;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.dtos.TenBlogDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Converter {
    public RecipeDTO toDTO(Recipe entity) {
        RecipeDTO dto = new RecipeDTO();
        dto.setRecipe_id(entity.getRecipeID());
        dto.setRecipe_thumbnail(entity.getRecipe_thumbnail());
        dto.setRecipe_content(entity.getRecipe_content());
        dto.setRecipe_difficulty(entity.getRecipe_difficulty());
        dto.setPortion_size(entity.getPortion_size());
        dto.setBaking_time_minutes(entity.getBaking_time_minutes());
        dto.setPortion_type(entity.getPortion_type());
        dto.setRecipe_title(entity.getRecipeTitle());
        dto.setPrep_time_minutes(entity.getPrep_time_minutes());
        dto.setUser_id(entity.getUser().getUserID());
        dto.setRecipe_categories_id(entity.getRecipeCategories().getRecipe_category_id());
        dto.setResting_time_minutes(entity.getResting_time_minutes());
        dto.setTime_created(entity.getTime());
        dto.setLast_name(entity.getUser().getLast_name());
        dto.setFirst_name(entity.getUser().getFirst_name());
        return dto;
    }
    public TenRecipeDTO toDTO10(Recipe entity) {
        TenRecipeDTO dto = new TenRecipeDTO();
        dto.setRecipe_thumbnail(entity.getRecipe_thumbnail());
        dto.setRecipe_title(entity.getRecipeTitle());
        dto.setLast_name(entity.getUser().getLast_name());
        dto.setFirst_name(entity.getUser().getFirst_name());
        dto.setRecipe_id(entity.getRecipeID());
        return dto;
    }
    public TenBlogDTO toDTO10BLOG(Blog entity){
        TenBlogDTO dto=new TenBlogDTO();
        dto.setBlog_thumbnail(entity.getBlog_thumbnail());
        dto.setBlog_content(entity.getBlog_content());
        dto.setBlog_title(entity.getBlog_title());
        dto.setFirst_name(entity.getUser().getFirst_name());
        dto.setLast_name(entity.getUser().getLast_name());
        dto.setBlog_id(entity.getBlogID());
        dto.setTime(entity.getTime());
        dto.setBlog_subtitle(entity.getBlog_subtitle());
        return dto;
    }
    public BlogDTO toDTOBLOG(Blog entity) {
        BlogDTO dto = new BlogDTO();
        dto.setBlog_id(entity.getBlogID());
        dto.setBlog_title(entity.getBlog_title());
        dto.setBlog_subtitle(entity.getBlog_subtitle());
        dto.setFirst_name(entity.getUser().getFirst_name());
        dto.setLast_name(entity.getUser().getLast_name());
        dto.setTime(entity.getTime());
        dto.setBlog_content(entity.getBlog_content());
        dto.setBlog_thumbnail(entity.getBlog_thumbnail());
        dto.setUser_id(entity.getUser().getUserID());
        return dto;
    }
}
