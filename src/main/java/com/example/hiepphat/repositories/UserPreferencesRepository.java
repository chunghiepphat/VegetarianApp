package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferencesRepository extends JpaRepository<UserPreference,Integer> {
}
