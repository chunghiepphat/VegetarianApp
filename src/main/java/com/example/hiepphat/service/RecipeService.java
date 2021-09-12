package com.example.hiepphat.service;

import com.example.hiepphat.dtos.RecipeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
public interface RecipeService {
    List<RecipeDTO>findAll(Pageable pageable);
    int totalItem();
}
