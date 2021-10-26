package com.example.hiepphat.service;

import com.example.hiepphat.dtos.ListMenuDTO;

import java.util.List;

public interface MenuRecipeService {
    List<ListMenuDTO>findById(int id);
}
