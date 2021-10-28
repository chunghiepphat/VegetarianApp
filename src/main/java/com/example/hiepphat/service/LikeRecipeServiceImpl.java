package com.example.hiepphat.service;

import com.example.hiepphat.Entity.LikeRecipe;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.ListLikeDTO;
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
             Recipe enties=recipeRepository.findByRecipeID(item);
             TenRecipeDTO dto=new TenRecipeDTO();
             dto.setRecipe_id(enties.getRecipeID());
             dto.setRecipe_thumbnail(enties.getRecipe_thumbnail());
             dto.setRecipe_title(enties.getRecipeTitle());
             dto.setFirst_name(enties.getUser().getFirstName());
             dto.setLast_name(enties.getUser().getLastName());
             dto.setTotalLike(recipeRepository.totalLike(enties.getRecipeID()));
             dto.setTime_created(enties.getTime());
             if(enties.isPrivate()==false&&enties.getStatus()==2){
                 results.add(dto);
             }
        }
        return results;
    }

    @Override
    public LikeRecipe findByRecipe_RecipeIDAndUser_UserID(long recipeID, int userID) {
        return likeRecipeRepository.findByRecipe_RecipeIDAndUser_UserID(recipeID,userID);
    }

    @Override
    public List<ListLikeDTO> viewListUserLike(long id) {
        List<ListLikeDTO>result=new ArrayList<>();
        List<LikeRecipe> entity=likeRecipeRepository.findByRecipe_RecipeID(id);
        for(LikeRecipe item:entity){
            ListLikeDTO dto=new ListLikeDTO();
            dto.setUser_id(item.getUser().getUserID());
            if(item.getRecipe().isPrivate()==false&&item.getRecipe().getStatus()==2){
                result.add(dto);
            }
        }
        return result;
    }


}
