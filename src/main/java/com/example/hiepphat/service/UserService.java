package com.example.hiepphat.service;


import com.example.hiepphat.Entity.User;

import org.springframework.stereotype.Service;


@Service
public interface UserService {
public User save(User user);

Boolean existsByEmail(String email);
    public User findByUser_id(int id);
}
