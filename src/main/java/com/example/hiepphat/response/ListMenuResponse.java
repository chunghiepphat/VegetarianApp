package com.example.hiepphat.response;

import com.example.hiepphat.dtos.ListMenuDTO;

import java.util.ArrayList;
import java.util.List;

public class ListMenuResponse {
    private List<ListMenuDTO>menu=new ArrayList<>();

    public List<ListMenuDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<ListMenuDTO> menu) {
        this.menu = menu;
    }
}
