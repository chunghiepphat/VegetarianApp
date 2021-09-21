package com.example.hiepphat.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Likes_Recipe")
public class LikeRecipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
