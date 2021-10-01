package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    @Query(value = "Select ingredient_id, ingredient_name,protein,fat,carb,calories from Ingredients where ingredient_id in (select ingredient_id from Recipes_Ingredients where recipe_id=?)",nativeQuery = true)
    List<Ingredient>getIngredient(long recipeID);
    boolean existsByIngredientName(String name);
    @Query(value = "Select ingredient_id from Ingredients where ingredient_name=?",nativeQuery = true)
    int findIngredientID(String name);
    @Query(value = "Select amount_in_mg from Recipes_Ingredients where recipe_id=?",nativeQuery = true)
    List<Integer> getAmount(long recipeID);
    Ingredient findByIngredientName(String name);
}
