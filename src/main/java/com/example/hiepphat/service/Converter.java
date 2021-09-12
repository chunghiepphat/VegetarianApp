package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.RecipeDTO;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public RecipeDTO toDTO(Recipe entity) {
        RecipeDTO dto = new RecipeDTO();
        dto.setRecipe_id(entity.getRecipe_id());
        dto.setRecipe_thumbnail(entity.getRecipe_thumbnail());
        dto.setRecipe_content(entity.getRecipe_content());
        dto.setRecipe_difficulty(entity.getRecipe_difficulty());
        dto.setPortion_size(entity.getPortion_size());
        dto.setBaking_time_minutes(entity.getBaking_time_minutes());
        dto.setPortion_type(entity.getPortion_type());
        dto.setRecipe_title(entity.getRecipe_title());
        dto.setPrep_time_minutes(entity.getPrep_time_minutes());
        dto.setUser_id(entity.getUser().getUser_id());
        dto.setRecipe_categories_id(entity.getRecipeCategories().getRecipe_category_id());
        dto.setResting_time_minutes(entity.getResting_time_minutes());
        return dto;
    }
}
