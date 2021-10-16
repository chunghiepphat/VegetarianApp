package com.example.hiepphat.response;

import com.example.hiepphat.dtos.MenuDTO;

import java.util.ArrayList;
import java.util.List;

public class MenuResponse {
    private List<MenuDTO>listRecipe=new ArrayList<>();

    public List<MenuDTO> getListRecipe() {
        return listRecipe;
    }

    public void setListRecipe(List<MenuDTO> listRecipe) {
        this.listRecipe = listRecipe;
    }
}
