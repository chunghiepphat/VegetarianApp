package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Ingredient;
import com.example.hiepphat.dtos.NutritionDTO;

public interface IngredientService {
    NutritionDTO getIngredientByRecipe(long recipeID);
    boolean existsByIngredient_name(String name);
    Ingredient save(Ingredient ingredient);
    int findIngredientID(String name);
    NutritionDTO findByIngredientName(String name);
    Ingredient findIngredientName(String name);
}
