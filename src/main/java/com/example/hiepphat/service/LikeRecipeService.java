package com.example.hiepphat.service;

import com.example.hiepphat.Entity.LikeRecipe;
import com.example.hiepphat.dtos.TenRecipeDTO;

import java.util.List;

public interface LikeRecipeService {
    List<TenRecipeDTO> findbestRecipe();

}
