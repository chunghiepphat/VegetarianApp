package com.example.hiepphat.response;

import com.example.hiepphat.dtos.RecipeDTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeResponse {
    private int page;
    private int totalPage;

    private List<RecipeDTO> listResult=new ArrayList<>();

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

    public List<RecipeDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<RecipeDTO> listResult) {
        this.listResult = listResult;
    }
}
