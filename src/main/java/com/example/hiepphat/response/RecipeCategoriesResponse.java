package com.example.hiepphat.response;

import com.example.hiepphat.dtos.RecipeCategoriesDTO;

import java.util.List;

public class RecipeCategoriesResponse {
    private List<RecipeCategoriesDTO> listResult;

    public List<RecipeCategoriesDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<RecipeCategoriesDTO> listResult) {
        this.listResult = listResult;
    }
}
