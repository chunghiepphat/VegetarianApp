package com.example.hiepphat.response;

import com.example.hiepphat.dtos.TenBlogDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    private List<TenRecipeDTO> listRecipe=new ArrayList<>();
    private List<TenBlogDTO> listBlog=new ArrayList<>();

    public List<TenRecipeDTO> getListRecipe() {
        return listRecipe;
    }

    public void setListRecipe(List<TenRecipeDTO> listRecipe) {
        this.listRecipe = listRecipe;
    }

    public List<TenBlogDTO> getListBlog() {
        return listBlog;
    }

    public void setListBlog(List<TenBlogDTO> listBlog) {
        this.listBlog = listBlog;
    }
}
