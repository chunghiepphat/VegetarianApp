package com.example.hiepphat.service;

import com.example.hiepphat.Entity.CommentBlog;
import com.example.hiepphat.Entity.CommentRecipe;
import com.example.hiepphat.dtos.CommentBlogDTO;
import com.example.hiepphat.dtos.CommentRecipeDTO;
import com.example.hiepphat.repositories.CommentRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<CommentRecipeDTO> getAllCommentRecipe(long id) {
        List<CommentRecipeDTO>result=new ArrayList<>();
        List<CommentRecipe>entities=commentRecipeRepository.findByRecipe_RecipeID(id);
        for(CommentRecipe item:entities){
            CommentRecipeDTO dto=new CommentRecipeDTO();
            dto.setFirst_name(item.getUser().getFirstName());
            dto.setLast_name(item.getUser().getLastName());
            dto.setRecipe_id(item.getRecipe().getRecipeID());
            dto.setUser_id(item.getUser().getUserID());
            dto.setContent(item.getContent());
            dto.setTime(item.getTime());
            dto.setId(item.getId());
            result.add(dto);
        }
        return result;
    }
}
