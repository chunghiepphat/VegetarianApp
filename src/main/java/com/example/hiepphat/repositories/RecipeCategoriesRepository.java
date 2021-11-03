package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.RecipeCategories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RecipeCategoriesRepository extends JpaRepository<RecipeCategories,Integer> {
     @Query(value = "SELECT recipe_category_id,recipe_category_name,recipe_category_thumbnail from Recipe_Categories ",nativeQuery = true)
     List<RecipeCategories> getAll();
     @Transactional
     @Modifying
     @Query(value = "INSERT INTO Recipe_Categories(recipe_category_name,recipe_category_thumbnail)VALUES(?,?)",nativeQuery = true)
     void saveRecipeCategory(@Param("recipe_category_name") String categoryName , @Param("recipe_category_thumbnail") String thumbnail);
     @Transactional
     @Modifying
     @Query(value = "DELETE FROM Recipe_Categories where recipe_category_id=?",nativeQuery = true)
     void deleteRecipeCategory(int id);
     @Query(value = "SELECT recipe_category_id,recipe_category_name,recipe_category_thumbnail from Recipe_Categories where recipe_category_id=?",nativeQuery = true)
     RecipeCategories getRecipeCategories(int id);
}
