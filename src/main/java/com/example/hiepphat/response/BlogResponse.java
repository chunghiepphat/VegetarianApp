package com.example.hiepphat.response;


import com.example.hiepphat.dtos.TenBlogDTO;


import java.util.ArrayList;
import java.util.List;

public class BlogResponse {
    private int page;
    private int totalPage;

    private List<TenBlogDTO> listResult=new ArrayList<>();

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

    public List<TenBlogDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<TenBlogDTO> listResult) {
        this.listResult = listResult;
    }
}
