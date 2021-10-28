package com.example.hiepphat.request;

import com.example.hiepphat.dtos.IngredientRecipeDTO;
import com.example.hiepphat.dtos.StepRecipeDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RecipeRequest {
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
    private boolean is_private;
    private List<IngredientRecipeDTO>ingredients=new ArrayList<>();

    public List<IngredientRecipeDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientRecipeDTO> ingredients) {
        this.ingredients = ingredients;
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

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }
}
