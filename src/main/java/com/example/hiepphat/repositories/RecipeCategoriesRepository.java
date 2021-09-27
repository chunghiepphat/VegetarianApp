package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.RecipeCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeCategoriesRepository extends JpaRepository<RecipeCategories,Long> {
     @Query(value = "SELECT recipe_category_id,recipe_category_name,recipe_category_thumbnail from RecipeCategories ",nativeQuery = true)
     List<RecipeCategories> getAll();
}
