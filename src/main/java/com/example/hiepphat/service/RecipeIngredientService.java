package com.example.hiepphat.service;

import com.example.hiepphat.Entity.RecipeIngredient;
import com.example.hiepphat.dtos.IngredientDTO;

import java.util.List;

public interface RecipeIngredientService {
    RecipeIngredient save(RecipeIngredient recipeIngredient);
    List<IngredientDTO> findByRecipe_RecipeID(long id);
    void deleteRecipeIngre(long id);
}
