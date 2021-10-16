package com.example.hiepphat.dtos;

import com.example.hiepphat.Entity.Menu;
import com.example.hiepphat.Entity.Recipe;

public class MenuDTO implements Comparable<MenuDTO> {
    private long recipe_id;
    private String recipe_thumbnail;
    private String meal_of_day;
    private String day_of_week;
    private int calo;
    public long getRecipe_id() {
        return recipe_id;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }

    public void setRecipe_id(long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_thumbnail() {
        return recipe_thumbnail;
    }

    public void setRecipe_thumbnail(String recipe_thumbnail) {
        this.recipe_thumbnail = recipe_thumbnail;
    }

    public String getMeal_of_day() {
        return meal_of_day;
    }

    public void setMeal_of_day(String meal_of_day) {
        this.meal_of_day = meal_of_day;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    @Override
    public int compareTo(MenuDTO o) {
        return o.getCalo()-this.getCalo();
    }
}
