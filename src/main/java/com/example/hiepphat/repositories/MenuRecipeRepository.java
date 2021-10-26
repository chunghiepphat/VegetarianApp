package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.MenuRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRecipeRepository extends JpaRepository<MenuRecipe,Integer> {
        List<MenuRecipe> findByMenu_User_UserID(int id);
}
