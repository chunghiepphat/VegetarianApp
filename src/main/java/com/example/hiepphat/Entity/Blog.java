package com.example.hiepphat.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private int blogID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String blogTitle;
    private String blog_subtitle;
    private String blog_content;
    private String blog_thumbnail;
    private boolean is_active;
    @Column(name = "time_created")
    private java.util.Date time;
    @Column(name = "time_updated")
    private Date timeUpdated;
    @Column(name = "is_private")
    private boolean isPrivate;
    private int status;

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
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

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
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
