package com.example.hiepphat.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListMenuDTO {
    private String date;
    private List<MenuDTO> listRecipe=new ArrayList<>();

    public List<MenuDTO> getListRecipe() {
        return listRecipe;
    }

    public void setListRecipe(List<MenuDTO> listRecipe) {
        this.listRecipe = listRecipe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
