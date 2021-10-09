package com.example.hiepphat.service;

import com.example.hiepphat.Entity.CommentRecipe;
import com.example.hiepphat.dtos.CommentRecipeDTO;

import java.util.List;

public interface CommentRecipeService {
    CommentRecipe save(CommentRecipe commentRecipe);
    void deleteComment(long id);
    List<CommentRecipeDTO>getAllCommentRecipe(long id);
    CommentRecipe findById(int id);
}
