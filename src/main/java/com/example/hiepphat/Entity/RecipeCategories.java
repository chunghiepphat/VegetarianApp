package com.example.hiepphat.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RecipeCategories")
public class RecipeCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipe_category_id;
    private String recipe_category_name;
    private String recipe_category_thumbnail;
    @OneToMany(mappedBy = "recipeCategories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recipe> recipes;
    public int getRecipe_category_id() {
        return recipe_category_id;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setRecipe_category_id(int recipe_category_id) {
        this.recipe_category_id = recipe_category_id;
    }

    public String getRecipe_category_name() {
        return recipe_category_name;
    }

    public void setRecipe_category_name(String recipe_category_name) {
        this.recipe_category_name = recipe_category_name;
    }

    public String getRecipe_category_thumbnail() {
        return recipe_category_thumbnail;
    }

    public void setRecipe_category_thumbnail(String recipe_category_thumbnail) {
        this.recipe_category_thumbnail = recipe_category_thumbnail;
    }
}
