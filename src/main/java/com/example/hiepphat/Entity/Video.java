package com.example.hiepphat.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_category_id")
    private VideoCategory videoCategory;
    @Column(name = "video_title")
    private String title;
    @Column(name = "video_link")
    private String videoLink;
    @Column(name = "video_description")
    private String description;
    @Column(name = "time_created")
    private Date timeCreated;
    @Column(name = "time_updated")
    private Date timeUpdated;
    @Column(name = "video_thumbnail")
    private String thumbnail;
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

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VideoCategory getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(VideoCategory videoCategory) {
        this.videoCategory = videoCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
