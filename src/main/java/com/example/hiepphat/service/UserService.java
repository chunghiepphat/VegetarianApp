package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Role;
import com.example.hiepphat.Entity.User;
import com.example.hiepphat.dtos.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserService {
public User save(User user);

Boolean existsByEmail(String email);
    public User findByUser_id(int id);
}
