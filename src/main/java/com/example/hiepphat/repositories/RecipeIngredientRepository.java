package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient,Integer> {
        List<RecipeIngredient> findByRecipe_RecipeID(long id);
        List<RecipeIngredient>findByIngredient_IngredientID(long id);
}
