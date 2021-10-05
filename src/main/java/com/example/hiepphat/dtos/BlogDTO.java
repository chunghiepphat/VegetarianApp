package com.example.hiepphat.dtos;

import com.example.hiepphat.Entity.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class BlogDTO {
    private long blog_id;
    private int user_id;
    private String blog_title;
    private String blog_subtitle;
    private String blog_content;
    private java.util.Date time;
    private String first_name;
    private String last_name;
    private String blog_thumbnail;
    private int totalLike;

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public long getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(long blog_id) {
        this.blog_id = blog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public String getBlog_subtitle() {
        return blog_subtitle;
    }

    public void setBlog_subtitle(String blog_subtitle) {
        this.blog_subtitle = blog_subtitle;
    }

    public String getBlog_content() {
        return blog_content;
    }

    public void setBlog_content(String blog_content) {
        this.blog_content = blog_content;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public String getBlog_thumbnail() {
        return blog_thumbnail;
    }

    public void setBlog_thumbnail(String blog_thumbnail) {
        this.blog_thumbnail = blog_thumbnail;
    }
}
