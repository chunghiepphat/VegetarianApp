package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.Entity.User;
import com.example.hiepphat.dtos.TenRecipeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> findTop10ByTimeLessThanEqualOrderByTimeDesc(Date date);
     public Recipe findByRecipeID(long id);
     List<Recipe> findTop10ByUser_UserIDOrderByTimeDesc(int userID);
    List<Recipe> findAllByUser_UserID(Pageable pageable, int userID);
    int countByUser_UserID(int userID);
    List<Recipe> findByRecipeTitleLike(String search);
    @Query(value = "Select recipe_id from Recipes where recipe_title=? and user_id=?",nativeQuery = true)
    long findrecipeID(String title,int userID);
    @Query(value = "Select * from Recipes where recipe_id in (select recipe_id from Likes_Recipe where user_id=? )"
            ,nativeQuery = true)
    List<Recipe>findLikedRecipe(int id);
}
