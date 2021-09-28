package com.example.hiepphat.service;

import com.example.hiepphat.dtos.IngredientDTO;

import java.util.List;

public interface IngredientService {
    IngredientDTO getIngredientByRecipe(long recipeID);

}
