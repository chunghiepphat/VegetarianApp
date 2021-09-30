package com.example.hiepphat.service;

import com.example.hiepphat.Entity.RecipeIngredient;
import com.example.hiepphat.repositories.RecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;


    @Override
    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }


}
