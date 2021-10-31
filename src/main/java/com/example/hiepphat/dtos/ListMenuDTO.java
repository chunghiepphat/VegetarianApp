package com.example.hiepphat.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListMenuDTO {
    private Date date;
    private List<MenuDTO> listRecipe=new ArrayList<>();

    public List<MenuDTO> getListRecipe() {
        return listRecipe;
    }

    public void setListRecipe(List<MenuDTO> listRecipe) {
        this.listRecipe = listRecipe;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
