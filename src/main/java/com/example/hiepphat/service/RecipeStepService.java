package com.example.hiepphat.service;

import com.example.hiepphat.Entity.RecipeStep;
import com.example.hiepphat.dtos.StepRecipeDTO;

import java.util.List;

public interface RecipeStepService {
    List<StepRecipeDTO> findByRecipe_RecipeID(long id);
}
