package com.example.hiepphat.response;

import com.example.hiepphat.dtos.TenBlogDTO;


import java.util.ArrayList;
import java.util.List;

public class TenBlogResponse {
    private List<TenBlogDTO> listResult=new ArrayList<>();

    public List<TenBlogDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<TenBlogDTO> listResult) {
        this.listResult = listResult;
    }
}
