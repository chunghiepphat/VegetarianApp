package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.UserAllergies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAllergiesRepository extends JpaRepository<UserAllergies,Integer> {
    List<UserAllergies>findByUser_UserID(int userID);
}
