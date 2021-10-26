package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRespository extends JpaRepository<Menu,Integer> {
    Menu findByUser_UserID(int id);
}
