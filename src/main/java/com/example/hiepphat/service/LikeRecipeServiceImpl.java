package com.example.hiepphat.service;

import com.example.hiepphat.Entity.LikeRecipe;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.TenRecipeDTO;
import com.example.hiepphat.repositories.LikeRecipeRepository;
import com.example.hiepphat.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LikeRecipeServiceImpl implements LikeRecipeService{
   @Autowired
    LikeRecipeRepository likeRecipeRepository;
   @Autowired
    RecipeRepository recipeRepository;
    @Override
    public List<TenRecipeDTO> findbestRecipe() {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Integer> listInt=likeRecipeRepository.findbestRecipe();
        for(Integer item:listInt){
//            TenRecipeDTO dto=new TenRecipeDTO();
//            dto.setFirst_name(item.getRecipe().getUser().getFirst_name());
//            dto.setLast_name(item.getRecipe().getUser().getLast_name());
//            dto.setRecipe_id(item.getRecipe().getRecipeID());
//            dto.setRecipe_title(item.getRecipe().getRecipe_title());
//            dto.setRecipe_thumbnail(item.getRecipe().getRecipe_thumbnail());
//            results.add(dto);
             Recipe enties=recipeRepository.findByRecipeID(item);
             TenRecipeDTO dto=new TenRecipeDTO();
             dto.setRecipe_id(enties.getRecipeID());
             dto.setRecipe_thumbnail(enties.getRecipe_thumbnail());
             dto.setRecipe_title(enties.getRecipeTitle());
             dto.setFirst_name(enties.getUser().getFirstName());
             dto.setLast_name(enties.getUser().getLastName());
             results.add(dto);
        }

        return results;
    }

    @Override
    public LikeRecipe findByRecipe_RecipeIDAndUser_UserID(long recipeID, int userID) {
        return likeRecipeRepository.findByRecipe_RecipeIDAndUser_UserID(recipeID,userID);
    }


}
