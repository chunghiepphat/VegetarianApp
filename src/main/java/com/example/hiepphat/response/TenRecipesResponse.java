package com.example.hiepphat.response;

import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.dtos.TenRecipeDTO;

import java.util.ArrayList;
import java.util.List;

public class TenRecipesResponse {
    private List<TenRecipeDTO> listResult=new ArrayList<>();

    public List<TenRecipeDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<TenRecipeDTO> listResult) {
        this.listResult = listResult;
    }
}
