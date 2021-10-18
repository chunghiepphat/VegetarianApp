package com.example.hiepphat.Entity;

import com.example.hiepphat.dtos.MenuDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Recipes")
public class Recipe implements Comparable<Recipe> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long recipeID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_category_id")
    private RecipeCategories recipeCategories;
    @Column(name = "recipe_title")
    private String recipeTitle;
    private String recipe_thumbnail;
    private String recipe_content;
    private int recipe_difficulty;
    private int portion_size;
    private String portion_type;
    private int prep_time_minutes;
    private int baking_time_minutes;
    private int resting_time_minutes;
    @Column(name = "total_calo")
    private int totalCalo;
    @Column(name = "time_created")
    private java.util.Date time;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "Recipes_Ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient>ingredients=new HashSet<>();
    @Column(name = "time_updated")
    private java.util.Date timeUpdated;
    public String getRecipeTitle() {
        return recipeTitle;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getTotalCalo() {
        return totalCalo;
    }

    public void setTotalCalo(int totalCalo) {
        this.totalCalo = totalCalo;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(long recipeID) {
        this.recipeID = recipeID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RecipeCategories getRecipeCategories() {
        return recipeCategories;
    }

    public void setRecipeCategories(RecipeCategories recipeCategories) {
        this.recipeCategories = recipeCategories;
    }



    public String getRecipe_thumbnail() {
        return recipe_thumbnail;
    }

    public void setRecipe_thumbnail(String recipe_thumbnail) {
        this.recipe_thumbnail = recipe_thumbnail;
    }

    public String getRecipe_content() {
        return recipe_content;
    }

    public void setRecipe_content(String recipe_content) {
        this.recipe_content = recipe_content;
    }

    public int getRecipe_difficulty() {
        return recipe_difficulty;
    }

    public void setRecipe_difficulty(int recipe_difficulty) {
        this.recipe_difficulty = recipe_difficulty;
    }

    public int getPortion_size() {
        return portion_size;
    }

    public void setPortion_size(int portion_size) {
        this.portion_size = portion_size;
    }

    public String getPortion_type() {
        return portion_type;
    }

    public void setPortion_type(String portion_type) {
        this.portion_type = portion_type;
    }

    public int getPrep_time_minutes() {
        return prep_time_minutes;
    }

    public void setPrep_time_minutes(int prep_time_minutes) {
        this.prep_time_minutes = prep_time_minutes;
    }

    public int getBaking_time_minutes() {
        return baking_time_minutes;
    }

    public void setBaking_time_minutes(int baking_time_minutes) {
        this.baking_time_minutes = baking_time_minutes;
    }

    public int getResting_time_minutes() {
        return resting_time_minutes;
    }

    public void setResting_time_minutes(int resting_time_minutes) {
        this.resting_time_minutes = resting_time_minutes;
    }

    public java.util.Date getTime() {
        return time;
    }

    public void setTime(java.util.Date time) {
        this.time = time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public java.util.Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(java.util.Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    @Override
    public int compareTo(Recipe o) {
        return o.getTotalCalo()-this.getTotalCalo();
    }
}
