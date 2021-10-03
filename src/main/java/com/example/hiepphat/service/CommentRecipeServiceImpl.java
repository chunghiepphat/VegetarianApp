package com.example.hiepphat.service;

import com.example.hiepphat.Entity.CommentRecipe;
import com.example.hiepphat.repositories.CommentRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentRecipeServiceImpl implements CommentRecipeService {
    @Autowired
    private CommentRecipeRepository commentRecipeRepository;
    @Override
    public CommentRecipe save(CommentRecipe commentRecipe) {
        return commentRecipeRepository.save(commentRecipe) ;
    }
}
