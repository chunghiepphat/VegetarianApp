package com.example.hiepphat.dtos;

import java.util.Date;

public class TenBlogDTO {
    private String blog_title;
    private String blog_thumbnail;
    private String blog_content;
    private String first_name;
    private String last_name;
    private String blog_subtitle;
    private int blog_id;
    private int totalLike;
    private Date time_created;
    private int status;
    private boolean is_private;
    private int user_id;
    private boolean is_like;

    public boolean isIs_like() {
        return is_like;
    }

    public void setIs_like(boolean is_like) {
        this.is_like = is_like;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }



    public String getBlog_subtitle() {
        return blog_subtitle;
    }

    public void setBlog_subtitle(String blog_subtitle) {
        this.blog_subtitle = blog_subtitle;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public int getBlog_id() {
        return blog_id;
    }


    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public String getBlog_thumbnail() {
        return blog_thumbnail;
    }

    public void setBlog_thumbnail(String blog_thumbnail) {
        this.blog_thumbnail = blog_thumbnail;
    }

    public String getBlog_content() {
        return blog_content;
    }

    public void setBlog_content(String blog_content) {
        this.blog_content = blog_content;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getTime_created() {
        return time_created;
    }

    public void setTime_created(Date time_created) {
        this.time_created = time_created;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }
}
