package com.example.hiepphat.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private long blogID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String blog_title;
    private String blog_subtitle;
    private String blog_content;
    private String blog_thumbnail;
    private boolean is_active;
    @Column(name = "time_created")
    private java.util.Date time;

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

    public long getBlogID() {
        return blogID;
    }

    public void setBlogID(long blogID) {
        this.blogID = blogID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
