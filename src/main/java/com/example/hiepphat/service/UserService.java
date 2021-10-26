package com.example.hiepphat.service;


import com.example.hiepphat.Entity.User;

import com.example.hiepphat.dtos.TenRecipeDTO;
import com.example.hiepphat.dtos.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
public User save(User user);

Boolean existsByEmail(String email);
    public User findByUser_id(int id);
    List<UserDTO> findAll(Pageable pageable);
    int totalItem();
}
