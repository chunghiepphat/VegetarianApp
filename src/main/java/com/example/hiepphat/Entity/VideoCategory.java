package com.example.hiepphat.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "VideoCategories")
public class VideoCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_category_id")
    private int id;
    @Column(name ="video_category_name")
    private String categoryName;
    @Column(name = "video_category_thumbnail")
    private String thumbnail;
    @OneToMany(mappedBy = "videoCategory", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Video> videos;

    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
