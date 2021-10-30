package com.example.hiepphat.Entity;

import javax.persistence.*;
@Entity
@Table(name = "Menus_Recipes")
public class MenuRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    @Column(name = "date_menu")
    private String date;
    @Column(name = "meal_of_day")
    private String mealOfday;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMealOfday() {
        return mealOfday;
    }

    public void setMealOfday(String mealOfday) {
        this.mealOfday = mealOfday;
    }
}
