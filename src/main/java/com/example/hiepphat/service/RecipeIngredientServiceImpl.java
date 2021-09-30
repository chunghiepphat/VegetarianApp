package com.example.hiepphat.service;

import com.example.hiepphat.Entity.RecipeIngredient;
import com.example.hiepphat.dtos.IngredientDTO;
import com.example.hiepphat.repositories.RecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;


    @Override
    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public List<IngredientDTO> findByRecipe_RecipeID(long id) {
           List<IngredientDTO>list=new ArrayList<>();
        List<RecipeIngredient>entities=recipeIngredientRepository.findByRecipe_RecipeID(id);
        for(RecipeIngredient items:entities){
            IngredientDTO result=new IngredientDTO();
            result.setIngredient_name(items.getIngredient().getIngredientName());
            result.setAmount_in_mg(items.getAmount());
            list.add(result);
        }
        return list;
    }


}
