package com.example.hiepphat.response;

import com.example.hiepphat.dtos.CommentRecipeDTO;

import java.util.ArrayList;
import java.util.List;

public class ListCommentRecipeResponse {
    private List<CommentRecipeDTO> listCommentRecipe=new ArrayList<>();

    public List<CommentRecipeDTO> getListCommentRecipe() {
        return listCommentRecipe;
    }

    public void setListCommentRecipe(List<CommentRecipeDTO> listCommentRecipe) {
        this.listCommentRecipe = listCommentRecipe;
    }
}
