package com.example.hiepphat.service;


import com.example.hiepphat.Entity.Recipe;

import com.example.hiepphat.dtos.RecipeCategoriesDTO;
import com.example.hiepphat.dtos.RecipeDTO;

import com.example.hiepphat.dtos.TenRecipeDTO;
import org.springframework.data.domain.Pageable;



import java.util.List;


public interface RecipeService {
    List<TenRecipeDTO>findAll(Pageable pageable);
    int totalItem();
    List<TenRecipeDTO>findTop10Records();
    RecipeDTO findrecipebyID(long id);
    List<TenRecipeDTO> findTop10ByUserOrderByTimeDesc(int userID);
    List<TenRecipeDTO> findAllByUser_UserID(Pageable pageable, int userID);
    int countByUser_UserID(int userID);
    List<TenRecipeDTO> findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String search,String fn,String ln);
     Recipe save(Recipe recipe);
    List<RecipeCategoriesDTO> getAllRecipeCategory();
    List<TenRecipeDTO>findLikedRecipe(int id);
    void deleteByRecipeID(long id);
    void deleteLike(long recipeID);
    int totalLike(long id);
    List<TenRecipeDTO> findTop10ByUserOrderByTimeDescOtherside(int userID);
    List<TenRecipeDTO> findAllByUser_UserIDOtherside(Pageable pageable, int userID);
    List<TenRecipeDTO>findAllAdmin(Pageable pageable);
    List<TenRecipeDTO>filterPreptime(String search,String fn,String ln,int prepTime);
    List<TenRecipeDTO>filterCategory(String search,String fn,String ln,String category);
    List<TenRecipeDTO>filterDifficulty(String search,String fn,String ln,int diff);
    List<TenRecipeDTO>filterPrep_Cate(String search,String fn,String ln,int prepTime,String category);
    List<TenRecipeDTO>filterPrep_Diff(String search,String fn,String ln,int prepTime,int diff);
    List<TenRecipeDTO>filterCate_Diff(String search,String fn,String ln,String category,int diff);
    List<TenRecipeDTO>filterALL(String search,String fn,String ln,String category,int diff,int prepTime);
    List<TenRecipeDTO>showALLRecipeUserbyAdmin(Pageable pageable,int userID);
}
