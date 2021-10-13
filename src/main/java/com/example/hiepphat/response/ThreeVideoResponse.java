package com.example.hiepphat.response;

import com.example.hiepphat.dtos.VideoDTO;

import java.util.ArrayList;
import java.util.List;

public class ThreeVideoResponse {
    private List<VideoDTO> listVideo=new ArrayList<>();

    public List<VideoDTO> getListVideo() {
        return listVideo;
    }

    public void setListVideo(List<VideoDTO> listVideo) {
        this.listVideo = listVideo;
    }
}
