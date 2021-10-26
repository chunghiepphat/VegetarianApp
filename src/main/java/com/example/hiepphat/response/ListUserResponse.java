package com.example.hiepphat.response;

import com.example.hiepphat.dtos.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class ListUserResponse {
    private int page;
    private int totalPage;
    private List<UserDTO> listUser=new ArrayList<>();

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

    public List<UserDTO> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserDTO> listUser) {
        this.listUser = listUser;
    }
}
