package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.LikeRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LikeRecipeRepository extends JpaRepository<LikeRecipe,Long> {
    @Query(value = "SELECT TOP 5 l.recipe_id FROM Likes_Recipe l,Recipes r where l.recipe_id=r.recipe_id and r.status=2 and r.is_private='false' GROUP BY l.recipe_id ORDER BY COUNT (l.user_id) DESC\n ",nativeQuery = true)
    List<Integer> findbestRecipe();
    List<LikeRecipe>findByRecipe_RecipeID(long id);
    LikeRecipe findByRecipe_RecipeIDAndUser_UserID(long recipeID,int userID);
    List<LikeRecipe>findByUser_UserID(int userID);
}
