package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeStepRepository extends JpaRepository<RecipeStep,Integer> {
    List<RecipeStep>findByRecipe_RecipeID(long id);
    RecipeStep findByRecipe_RecipeIDAndAndStepIndex(long recipeID,int index);
}
