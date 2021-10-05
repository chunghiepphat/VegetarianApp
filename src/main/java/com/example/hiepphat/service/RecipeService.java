package com.example.hiepphat.service;

import com.example.hiepphat.Entity.LikeRecipe;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.Entity.User;
import com.example.hiepphat.dtos.RecipeCategoriesDTO;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.dtos.TenBlogDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<TenRecipeDTO>findAll(Pageable pageable);
    int totalItem();
    List<TenRecipeDTO>findTop10Records();
    RecipeDTO findrecipebyID(long id);
    List<TenRecipeDTO> findTop10ByUserOrderByTimeDesc(int userID);
    List<TenRecipeDTO> findAllByUser_UserID(Pageable pageable, int userID);
    int countByUser_UserID(int userID);
    List<TenRecipeDTO> findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String search,String fn,String ln);
    public Recipe save(Recipe recipe);
    List<RecipeCategoriesDTO> getAllRecipeCategory();
    long findrecipeID(String title,int user_id);
    List<TenRecipeDTO>findLikedRecipe(int id);
    void deleteByRecipeID(long id);
    void deleteLike(long recipeID);
    int totalLike(long id);
}
