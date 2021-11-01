package com.example.hiepphat.repositories;


import com.example.hiepphat.Entity.Recipe;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    @Query(value = "SELECT TOP 10 * from Recipes where time_created<=? and status=2 and is_private='false' order by time_created desc",nativeQuery = true)
    List<Recipe> find10recipes(Date date);
     public Recipe findByRecipeID(long id);
     List<Recipe> findTop10ByUser_UserIDOrderByTimeDesc(int userID);
    List<Recipe> findAllByUser_UserID(Pageable pageable, int userID);
    int countByUser_UserID(int userID);
    List<Recipe> findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String search,String fn,String ln);
    @Query(value = "Select recipe_id from Recipes where recipe_title=? and user_id=? and time_created=?",nativeQuery = true)
    Long findrecipeID(String title, int userID, java.util.Date time);
    @Query(value = "Select * from Recipes where recipe_id in (select recipe_id from Likes_Recipe where user_id=? )"
            ,nativeQuery = true)
    List<Recipe>findLikedRecipe(int id);
    @Query(value = "SELECT COUNT(user_id) from Likes_Recipe where recipe_id=?",nativeQuery = true)
    int totalLike(long id);
    List<Recipe>findByTotalCaloLessThanEqualAndTotalCaloGreaterThan(int less,int greater);
    @Query(value = "Select * from Recipes where recipe_id in (select recipe_id from Likes_Recipe GROUP BY recipe_id HAVING  COUNT(user_id)>2)"
            ,nativeQuery = true)
    List<Recipe>findLikeGreater2();
}
