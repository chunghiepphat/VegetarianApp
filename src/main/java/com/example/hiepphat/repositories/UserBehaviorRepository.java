package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.UserBehavior;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBehaviorRepository extends JpaRepository<UserBehavior,Integer> {
    UserBehavior findByUser_UserIDAndQuerry(int userID,String querry);
}
