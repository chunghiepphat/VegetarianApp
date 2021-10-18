package com.example.hiepphat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RecipeDTO {
    private long recipe_id;
    private int user_id;
    private int recipe_categories_id;
    private String recipe_title;
    private String recipe_thumbnail;
    private List<StepRecipeDTO>steps=new ArrayList<>();
    private int recipe_difficulty;
    private int portion_size;
    private String portion_type;
    private int prep_time_minutes;
    private int baking_time_minutes;
    private int resting_time_minutes;
    private java.util.Date time_created;
    private java.util.Date time_updated;
    private String first_name;
    private String last_name;
    private NutritionDTO nutrition;
    private List<IngredientDTO> ingredients=new ArrayList<>();
    private int totalLike;
    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public NutritionDTO getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionDTO nutrition) {
        this.nutrition = nutrition;
    }

    public java.util.Date getTime_created() {
        return time_created;
    }

    public void setTime_created(java.util.Date time_created) {
        this.time_created = time_created;
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

    public long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRecipe_categories_id() {
        return recipe_categories_id;
    }

    public void setRecipe_categories_id(int recipe_categories_id) {
        this.recipe_categories_id = recipe_categories_id;
    }

    public String getRecipe_title() {
        return recipe_title;
    }

    public void setRecipe_title(String recipe_title) {
        this.recipe_title = recipe_title;
    }

    public String getRecipe_thumbnail() {
        return recipe_thumbnail;
    }

    public void setRecipe_thumbnail(String recipe_thumbnail) {
        this.recipe_thumbnail = recipe_thumbnail;
    }

    public List<StepRecipeDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepRecipeDTO> steps) {
        this.steps = steps;
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

    public java.util.Date getTime_updated() {
        return time_updated;
    }

    public void setTime_updated(java.util.Date time_updated) {
        this.time_updated = time_updated;
    }
}
