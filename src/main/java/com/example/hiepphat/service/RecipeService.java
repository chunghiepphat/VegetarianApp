package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<TenRecipeDTO>findAll(Pageable pageable);
    int totalItem();
    List<TenRecipeDTO>findTop10Records();
    RecipeDTO findrecipebyID(long id);
}
