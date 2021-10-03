package com.example.hiepphat.service;

import com.example.hiepphat.Entity.CommentRecipe;
import com.example.hiepphat.repositories.CommentRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentRecipeServiceImpl implements CommentRecipeService {
    @Autowired
    private CommentRecipeRepository commentRecipeRepository;
    @Override
    public CommentRecipe save(CommentRecipe commentRecipe) {
        return commentRecipeRepository.save(commentRecipe) ;
    }

    @Override
    public void deleteComment(long id) {
        List<CommentRecipe> entities=commentRecipeRepository.findByRecipe_RecipeID(id);
        for(CommentRecipe item:entities){
            commentRecipeRepository.deleteById(item.getId());
        }

    }
}
