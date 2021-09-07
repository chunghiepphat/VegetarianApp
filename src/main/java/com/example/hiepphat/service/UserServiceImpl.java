package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Role;
import com.example.hiepphat.Entity.User;
import com.example.hiepphat.repositories.RoleRepository;
import com.example.hiepphat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserRepository userRepository;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
