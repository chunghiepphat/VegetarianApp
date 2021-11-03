package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.BlogDTO;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.dtos.TenBlogDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;
import org.springframework.stereotype.Component;



@Component
public class Converter {
    public RecipeDTO toDTO(Recipe entity) {
        RecipeDTO dto = new RecipeDTO();
        dto.setRecipe_id(entity.getRecipeID());
        dto.setRecipe_thumbnail(entity.getRecipe_thumbnail());
        dto.setRecipe_difficulty(entity.getRecipeDifficulty());
        dto.setPortion_size(entity.getPortion_size());
        dto.setBaking_time_minutes(entity.getBaking_time_minutes());
        dto.setPortion_type(entity.getPortion_type());
        dto.setRecipe_title(entity.getRecipeTitle());
        dto.setPrep_time_minutes(entity.getPrepTime());
        dto.setUser_id(entity.getUser().getUserID());
        dto.setRecipe_categories_id(entity.getRecipeCategories().getRecipeCategoryID());
        dto.setResting_time_minutes(entity.getResting_time_minutes());
        dto.setTime_created(entity.getTime());
        dto.setLast_name(entity.getUser().getLastName());
        dto.setFirst_name(entity.getUser().getFirstName());
        dto.setTime_updated(entity.getTimeUpdated());
        dto.setIs_private(entity.isPrivate());
        dto.setStatus(entity.getStatus());
        return dto;
    }
    public TenRecipeDTO toDTO10(Recipe entity) {
        TenRecipeDTO dto = new TenRecipeDTO();
        dto.setRecipe_thumbnail(entity.getRecipe_thumbnail());
        dto.setRecipe_title(entity.getRecipeTitle());
        dto.setLast_name(entity.getUser().getLastName());
        dto.setFirst_name(entity.getUser().getFirstName());
        dto.setRecipe_id(entity.getRecipeID());
        dto.setTime_created((entity.getTime()));
        dto.setIs_private(entity.isPrivate());
        dto.setStatus(entity.getStatus());
        dto.setUser_id(entity.getUser().getUserID());
        return dto;
    }
    public TenBlogDTO toDTO10BLOG(Blog entity){
        TenBlogDTO dto=new TenBlogDTO();
        dto.setBlog_thumbnail(entity.getBlog_thumbnail());
        dto.setBlog_content(entity.getBlog_content());
        dto.setBlog_title(entity.getBlogTitle());
        dto.setFirst_name(entity.getUser().getFirstName());
        dto.setLast_name(entity.getUser().getLastName());
        dto.setBlog_id(entity.getBlogID());
        dto.setTime_created(entity.getTime());
        dto.setBlog_subtitle(entity.getBlog_subtitle());
        dto.setStatus(entity.getStatus());
        dto.setIs_private(entity.isPrivate());
        dto.setUser_id(entity.getUser().getUserID());
        return dto;
    }
    public BlogDTO toDTOBLOG(Blog entity) {
        BlogDTO dto = new BlogDTO();
        dto.setBlog_id(entity.getBlogID());
        dto.setBlog_title(entity.getBlogTitle());
        dto.setBlog_subtitle(entity.getBlog_subtitle());
        dto.setFirst_name(entity.getUser().getFirstName());
        dto.setLast_name(entity.getUser().getLastName());
        dto.setTime(entity.getTime());
        dto.setBlog_content(entity.getBlog_content());
        dto.setBlog_thumbnail(entity.getBlog_thumbnail());
        dto.setUser_id(entity.getUser().getUserID());
        dto.setTime_updated(entity.getTimeUpdated());
        dto.setIs_private(entity.isPrivate());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
