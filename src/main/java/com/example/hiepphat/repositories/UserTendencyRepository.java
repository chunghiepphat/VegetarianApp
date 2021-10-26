package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.UserTendency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTendencyRepository extends JpaRepository<UserTendency,Integer> {
    UserTendency findByUser_UserIDAndIngredient_IngredientID(int userID,long ingredientID);
    List<UserTendency>findByUser_UserIDAndFrequencyGreaterThanEqual(int userID,int frequency);
}
