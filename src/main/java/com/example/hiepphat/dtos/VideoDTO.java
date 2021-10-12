package com.example.hiepphat.dtos;

public class VideoDTO {
    private int user_id;
    private int video_category_id;
    private String video_title;
    private String video_link;
    private String video_description;

    public String getVideo_description() {
        return video_description;
    }

    public void setVideo_description(String video_description) {
        this.video_description = video_description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVideo_category_id() {
        return video_category_id;
    }

    public void setVideo_category_id(int video_category_id) {
        this.video_category_id = video_category_id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }
}
