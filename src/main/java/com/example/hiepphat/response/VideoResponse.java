package com.example.hiepphat.response;

import com.example.hiepphat.dtos.VideoDTO;

import java.util.ArrayList;
import java.util.List;

public class VideoResponse {
    private int page;
    private int totalPage;
    private List<VideoDTO>listVideo=new ArrayList<>();

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

    public List<VideoDTO> getListVideo() {
        return listVideo;
    }

    public void setListVideo(List<VideoDTO> listVideo) {
        this.listVideo = listVideo;
    }
}
