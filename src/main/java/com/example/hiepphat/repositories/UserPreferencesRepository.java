package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPreferencesRepository extends JpaRepository<UserPreference,Integer> {
    List<UserPreference>findByUser_UserID(int userID);
}
