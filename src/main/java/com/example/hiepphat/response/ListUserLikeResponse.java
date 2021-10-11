package com.example.hiepphat.response;

import com.example.hiepphat.dtos.LikeBlogDTO;
import com.example.hiepphat.dtos.ListLikeDTO;

import java.util.ArrayList;
import java.util.List;

public class ListUserLikeResponse {
    private int totalLike;
    private List<ListLikeDTO>listUserlike=new ArrayList<>();

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public List<ListLikeDTO> getListUserlike() {
        return listUserlike;
    }

    public void setListUserlike(List<ListLikeDTO> listUserlike) {
        this.listUserlike = listUserlike;
    }
}
