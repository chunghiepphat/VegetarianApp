package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {

}
