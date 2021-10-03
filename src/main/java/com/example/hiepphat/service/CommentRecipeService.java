package com.example.hiepphat.service;

import com.example.hiepphat.Entity.CommentRecipe;

import java.util.List;

public interface CommentRecipeService {
    CommentRecipe save(CommentRecipe commentRecipe);
    void deleteComment(long id);
}
