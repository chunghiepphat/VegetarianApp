package com.example.hiepphat.response;

import com.example.hiepphat.dtos.ListMenuDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListMenuResponse {
    private Date startDate;
    private Date endDate;
    private List<ListMenuDTO>menu=new ArrayList<>();

    public List<ListMenuDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<ListMenuDTO> menu) {
        this.menu = menu;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
