package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.Entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
}
