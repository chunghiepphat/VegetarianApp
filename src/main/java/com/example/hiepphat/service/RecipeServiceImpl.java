package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.LikeRecipe;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.dtos.TenBlogDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;
import com.example.hiepphat.repositories.LikeRecipeRepository;
import com.example.hiepphat.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{
@Autowired
RecipeRepository recipeRepository;
    @Autowired
    private Converter converter;
    @Autowired
    LikeRecipeRepository likeRecipeRepository;
    @Override
    public List<TenRecipeDTO> findAll(Pageable pageable) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entites=recipeRepository.findAll(pageable).getContent();
        for(Recipe item:entites){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            results.add(recipeDTO);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int)recipeRepository.count();
    }

    @Override
    public List<TenRecipeDTO> findTop10Records() {
        Date date=new Date(new java.util.Date().getTime());
          List<TenRecipeDTO> results=new ArrayList<>();
            List<Recipe> entities=recipeRepository.findTop10ByTimeLessThanEqualOrderByTimeDesc(date);
            for (Recipe item: entities){
                TenRecipeDTO recipeDTO= converter.toDTO10(item);
                results.add(recipeDTO);
            }
            return results;
    }

    @Override
    public RecipeDTO findrecipebyID(long id) {
        Recipe enties=recipeRepository.findByRecipeID(id);
        RecipeDTO recipeDTO= converter.toDTO(enties);
        return recipeDTO;
    }

    @Override
    public List<TenRecipeDTO> findTop10ByUserOrderByTimeDesc(int userID) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entities=recipeRepository.findTop10ByUser_UserIDOrderByTimeDesc(userID);
        for (Recipe item: entities){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            results.add(recipeDTO);
        }
        return results;
    }

    @Override
    public List<TenRecipeDTO> findAllByUser_UserID(Pageable pageable, int userID) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entites=recipeRepository.findAllByUser_UserID(pageable,userID);
        for(Recipe item:entites){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            results.add(recipeDTO);

        }

        return results;
    }


    @Override
    public int countByUser_UserID(int userID) {
        return recipeRepository.countByUser_UserID(userID);
    }




}
