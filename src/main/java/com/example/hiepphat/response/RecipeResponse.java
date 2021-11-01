package com.example.hiepphat.response;


import com.example.hiepphat.dtos.TenRecipeDTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeResponse {
    private int page;
    private int totalPage;

    private List<TenRecipeDTO> listResult=new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<TenRecipeDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<TenRecipeDTO> listResult) {
        this.listResult = listResult;
    }
}
