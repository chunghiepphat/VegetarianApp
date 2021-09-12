package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
public class RecipeServiceImpl implements RecipeService{
@Autowired
RecipeRepository recipeRepository;
    @Autowired
    private Converter converter;

    @Override
    public List<RecipeDTO> findAll(Pageable pageable) {
        List<RecipeDTO> results=new ArrayList<>();
        List<Recipe> entites=recipeRepository.findAll(pageable).getContent();
        for(Recipe item:entites){
            RecipeDTO recipeDTO= converter.toDTO(item);
            results.add(recipeDTO);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int)recipeRepository.count();
    }
}
