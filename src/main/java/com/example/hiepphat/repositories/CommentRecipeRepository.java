package com.example.hiepphat.repositories;


import com.example.hiepphat.Entity.CommentRecipe;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

public interface CommentRecipeRepository extends JpaRepository<CommentRecipe,Integer> {
    List<CommentRecipe> findByRecipe_RecipeID(long id);
    void deleteById(int id);
    CommentRecipe findByRecipe_RecipeIDAndUser_UserIDAndTime(long recipeID, int userID, Date date);
    CommentRecipe findById(int id);
    List<CommentRecipe> findByRecipe_RecipeIDOrderByTimeDesc(long id);
}
