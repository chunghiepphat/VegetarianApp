package com.example.hiepphat.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListMenuDTO {
    private String day_of_week;
    private List<MenuDTO> listRecipe=new ArrayList<>();

    public List<MenuDTO> getListRecipe() {
        return listRecipe;
    }

    public void setListRecipe(List<MenuDTO> listRecipe) {
        this.listRecipe = listRecipe;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }
}
