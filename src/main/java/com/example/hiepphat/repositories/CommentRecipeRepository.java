package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.CommentRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRecipeRepository extends JpaRepository<CommentRecipe,Integer> {
    List<CommentRecipe> findByRecipe_RecipeID(long id);
    void deleteById(int id);
}
