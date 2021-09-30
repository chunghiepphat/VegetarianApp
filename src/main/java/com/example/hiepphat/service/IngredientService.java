package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Ingredient;
import com.example.hiepphat.dtos.IngredientDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientService {
    IngredientDTO getIngredientByRecipe(long recipeID);
    boolean existsByIngredient_name(String name);
    Ingredient save(Ingredient ingredient);
    int findIngredientID(String name);
}
